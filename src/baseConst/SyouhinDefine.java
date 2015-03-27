package baseConst;

/**
 *
 * @author Administrator
 * 商品マスタ(goods_mst)の列見出しおよびＳＱＬを定義
 */
public interface SyouhinDefine {

	//商品マスタを検索する際の、全フィールドを表示するＳＱＬのベースとなる
	String GOODS_SELECT_BASE =
			"select goods_id,goods_name,goods_group_name,cost,wholesale from goods_mst ";

	//商品マスタのIDと名前を検索するＳＱＬ
	String GOODS_SELECT_PARTS =
	"select goods_id, goods_name from goods_mst ";

	//商品マスタ件数取得
	String GOODS_SELECT_COUNT = "select count(*) from goods_mst ";

	//商品マスタ追加用ＳＱＬ
	String GOODS_INSERT ="insert into  goods_mst (goods_id,goods_name,goods_group_name,cost,wholesale) " +
	"values(? , ? , ? , ? , ?)";

	//商品マスタ削除用ＳＱＬ
	String GOODS_DELETE = "delete from goods_mst where goods_id = ?";

	//商品マスタ更新用ＳＱＬ
	String GOODS_UPDATE = "update goods_mst set ";


	//商品マスタ見出し用
	String[] HEAD_ALL = {"商品ID","商品名","グループ名","仕入単価","販売単価"};
	String[] HEAD_PARTS = {"商品ID","商品名"};
}
