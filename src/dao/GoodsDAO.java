package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseConst.SQLDefine;
import baseConst.SyouhinDefine;
import beans.DTO;
import beans.GoodsDTO;

/**
 * 商品マスタ（goods_mst）に関するＤＢアクセス全般のメソッド群
 * DataHandlerを継承し、SQLDefine,SyouhinDefineを実装している
 */
public class GoodsDAO extends DataHandler implements SQLDefine,SyouhinDefine{


	/**
	 * GoodsDetailMappingはGoodsDAOでのみ使用されるので
	 * インナークラスとして定義している
	 *
	 */
	private class GoodsDetailMapping implements ResultSetBeanMapping<GoodsDTO> {

		/**
		 * ResultSetを元にオブジェクトを作成する
		 * 商品マスタ（goods_mst）の全フィールド取得用
		 * @param rs
		 */
		public GoodsDTO createFromResultSet(ResultSet rs)
		throws SQLException {

			GoodsDTO goods = new GoodsDTO();
			goods.setGoodsId(rs.getString("goods_id"));
			goods.setGoodsName(rs.getString("goods_name"));
			goods.setGoodsGroupName(rs.getString("goods_group_name"));
			goods.setCost(rs.getString("cost"));
			goods.setWholesale(rs.getString("wholesale"));

			return goods;
		}
	}

	/**
	 * GoodsDetailMappingはGoodsDAOでのみ使用されるので
	 * インナークラスとして定義している
	 * 商品マスタ（goods_mst）の商品ID、商品名の取得用
	 */
	private class GoodsMapping implements ResultSetBeanMapping<GoodsDTO> {

		public GoodsDTO createFromResultSet(ResultSet rs)
		throws SQLException {

			GoodsDTO goods = new GoodsDTO();
			goods.setGoodsId(rs.getString("goods_id"));
			goods.setGoodsName(rs.getString("goods_name"));

			return goods;
		}
	}


	/**
	 * 商品マスタ全件検索メソッド
	 * @param なし
	 * @return ArrayList<GoodsDTO>　全フィールドのリスト
	 */
	public ArrayList<GoodsDTO> select() {
		ArrayList<String> params = new ArrayList<String>();
		String sql = GOODS_SELECT_BASE;
		sql = sql + " order by goods_id ";
		return super.execQuery(sql,params,new GoodsDetailMapping());
	}

	/**
	 * 商品マスタ検索用メソッド(条件付き検索)
	 * @param ArrayList<String>　検索条件のリスト
	 * @return ArrayList<GoodsDTO>　全フィールドのリスト
	 */
	public ArrayList<GoodsDTO> select(ArrayList<String> params) {
		String sql = GOODS_SELECT_BASE;

		if(params != null){
			sql = sql + " where goods_id = ? order by goods_id ";
		}
		return execQuery(sql,params,new GoodsDetailMapping());
	}

	/**
	 * 商品マスタ検索用メソッド(商品名のあいまい検索)
	 * @param String　商品名
	 * @return ArrayList<GoodsDTO>　商品ID、商品名が入ったリスト
	 */
	public ArrayList<GoodsDTO> selectLike(String sName) {
		String sql = GOODS_SELECT_PARTS;
		ArrayList<String> params = new ArrayList<String>();

		sql = sql + " where goods_name like ? order by goods_id";
		params.add("%" + sName + "%");

		return super.execQuery(sql,params,new GoodsMapping());
	}

	/**
	 * データ件数チェック用メソッド
	 * @param  ArrayList<String>　検索条件のリスト
	 * @return int　処理件数
	 */
	public int kensuCheck(ArrayList<String> params) {
		String sql = GOODS_SELECT_COUNT;

		if(params.size() != 0){
			sql = sql + " where goods_id = ?";
		}

		int cnt = 0;
		super.setDb();
		try {
			ResultSet rs = getPs(sql, params).executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			cnt = -1;
		}finally{
			super.closeDB();
		}

		return cnt;
	}


	/**
	 * 商品マスタ追加用メソッド
	 * @param DTO DTOインターフェースを実装したインスタンス
	 * @return int 処理件数
	 */
	public int insert(DTO Dto) {

		GoodsDTO sDto = (GoodsDTO)Dto;
		ArrayList<String> params = new ArrayList<String>();
		String sql = GOODS_INSERT;

		params.add(sDto.getGoodsId());
		params.add(sDto.getGoodsName());
		params.add(sDto.getGoodsGroupName());
		if(sDto.getCost() == null){
			params.add(null);
		}else{
			params.add(sDto.getCost().toString());
		}
		if(sDto.getWholesale() == null){
			params.add(null);
		}else{
			params.add(sDto.getWholesale().toString());
		}

		int cnt = 0;
		try {
			super.setDb();				//BDへ接続
			cnt = execUpdate(sql,params);		//SQLの実行
			reflectProc(cnt);					//戻り値により、commitまたはrollback
		} catch (SQLException e) {
			cnt = -1;
		} finally {
			closeDB();
		}
		return cnt;				//処理件数を戻す
	}

	/**
	 * 条件付き削除用メソッド
	 * @param  String　商品ID
	 * @return int　処理件数
	 */
	public int delete(String id) {
		String sql = GOODS_DELETE;
		ArrayList<String> params = new ArrayList<String>();
		params.add(id);

		int cnt = 0;
		try {
			super.setDb();
			cnt = execUpdate(sql,params);
			reflectProc(cnt);
		} catch (SQLException e) {
			cnt = -1;
		} finally {
			closeDB();
		}
		return cnt;
	}


	/**
	 * 更新用メソッド
	 * @param  DTO DTOインターフェースを実装したインスタンス
	 * @return int　処理件数
	 */
	public int update(DTO Dto) {
		GoodsDTO sDto = (GoodsDTO)Dto;
		String sql = GOODS_UPDATE;
		ArrayList<String> params = new ArrayList<String>();
		ArrayList<String> setFild = new ArrayList<String>();

		if ((sDto.getGoodsName() != null) && (sDto.getGoodsName().length() != 0)){
			setFild.add("goods_name = ? ");
			params.add(sDto.getGoodsName());
		}

		if ((sDto.getGoodsGroupName() != null) && (sDto.getGoodsGroupName().length() != 0)){
			setFild.add("goods_group_name = ? ");
			params.add(sDto.getGoodsGroupName());
		}

		if (sDto.getCost() != null ){
			setFild.add("cost = ? ");
			params.add(sDto.getCost().toString());
		}

		if (sDto.getWholesale() != null){
			setFild.add("wholesale = ? ");
			params.add(sDto.getWholesale().toString());
		}

		int setSize = setFild.size();

		String strSetFild = "";
		if(setSize > 0){
			strSetFild =  setFild.get(0);
			for(int i = 1 ; i < setSize ; i++){
				strSetFild = strSetFild + " ," + setFild.get(i);
			}
			strSetFild += " where goods_id = ? ";
		}
		params.add(sDto.getGoodsId());
		sql = sql+strSetFild;

		int cnt = 0;
		try {
			super.setDb();
			cnt = execUpdate(sql,params);
			reflectProc(cnt);
		} catch (SQLException e) {
			cnt = -1;
		} finally {
			closeDB();
		}
		return cnt;
	}
}
