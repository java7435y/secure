package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetをJavaBeansにするcreateFromResultSetメソッド
 * を宣言したインターフェース
 * @author Administrator
 *
 * @param <T> 使用する型を限定させる
 */
public interface ResultSetBeanMapping<T> {

	/**
	 * ResultSetを元にオブジェクトを作成するメソッドを作る
	 */
    public T createFromResultSet(ResultSet rs)
            throws SQLException;

}
