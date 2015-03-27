package dao;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Administrator
 * データベースアクセスをするクラス
 * 全てのDAOはこのクラスを継承する
 */
public abstract class DataHandler {

	private Connection db = null;			// ＤＢ接続用
	private PreparedStatement ps = null;	// ＳＱＬ文発行用

	/**
	 *  コネクションオブジェクトを設定
	 */
	public void setDb() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/db");
			db = ds.getConnection();
			db.setAutoCommit(false);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * @return　SQL発行用オブジェクトを返す
	 * @param sql	SQL文
	 * @param params<T>	プレースフォルダに引き渡す文字列
	 */
	protected PreparedStatement getPs(String sql, ArrayList<String> params)
			throws SQLException {
		ps = db.prepareStatement(sql);
		for (int i = 0; i < params.size(); i++) {
			ps.setString((i + 1), params.get(i));
		}
		return ps;
	}

	/**
	 * データーベースを閉じる
	 */
	protected void closeDB() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param sql	SQL文
	 * @param params<T>	プレースフォルダに引き渡す文字列
	 * @return（処理件数）を戻す
	 */
	protected int execUpdate(String sql, ArrayList<String> params) {
		int count = 0;
		try {
			ps = getPs(sql, params);
			count = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			count = -1;
		}
		return count;
	}

	/**
	 * 検索SQLを発行して、結果をオブジェクトのListに入れて返す。
	 * ResultSetからListへの変換は、ResultSetBeanMappingが行う
	 *
	 * @param sql	SQL文
	 * @param params<T>	プレースフォルダに引き渡す文字列
	 * @param mapping<T> 1レコードの情報（beans）オブジェクトを返すクラス
	 */
	protected <T> ArrayList<T> execQuery(String sql, ArrayList<String> params,
			ResultSetBeanMapping<T> mapping) {

		ArrayList<T> list = null;
		try {
			list = new ArrayList<T>();
			setDb();
			ps = getPs(sql, params);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				T bean = mapping.createFromResultSet(rs);

				list.add(bean);
			}


		} catch (SQLException e) {
			System.out.println("DataHandler=" + e);
		} finally {
			closeDB();
		}
		return list;
	}


	/**
	 * 引数retが-1ならロールバックし、
	 * 処理件数があればDBへ反映させる
	 * その後コネクションを閉じる
	 * @param ret
	 * @throws SQLException
	 */
	protected void reflectProc(int ret) throws SQLException {
		if (ret == -1) {
			db.rollback();
		} else {
			db.commit();
		}
		closeDB();
	}

}
