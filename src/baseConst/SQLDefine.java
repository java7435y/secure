package baseConst;

import java.util.ArrayList;
import beans.DTO;

/**
 *
 * @author Administrator
 * 実装したクラスは追加、更新、削除、全件検索、条件検索メソッドを強制する
 */
public interface SQLDefine {

	int insert(DTO dto);

	int update(DTO dto);

	int delete(String id);

	ArrayList<?> select();

	ArrayList<?> select(ArrayList<String> params);

}
