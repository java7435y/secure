package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDAO;

public class Serv06 extends HttpServlet {

	private static final long serialVersionUID = 4470601410890705806L;

	/**
	 * Serv06のサーブレットに直接アクセスされた場合に入力画面へ戻すため、
	 * doGetにsendRedirectを記述
	 */
	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException , IOException{
		response.sendRedirect(request.getContextPath()+"/JSP/Serv06Jstl.jsp");
	}

	/**
	 * ｐｏｓｔ送信時のメソッド
	 */
	public void doPost(HttpServletRequest request , HttpServletResponse response)
			throws ServletException , IOException{

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String err_msg = "";
		int errCnt = 0;
		String iniUrl = "/JSP/Serv06Jstl.jsp";

		//idが取得できない（null）ということは不正アクセスの可能性があるので
		// sendRedirectを記述し、入力画面へ戻す
		String id = request.getParameter("id");
		if(id == null){
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}


		//入力チｪック
		if(id.length() == 0){
			err_msg = "商品IDを入力してください";
			errCnt ++;
			session.setAttribute("msgId_err" , err_msg);
		}else{
			session.setAttribute("msgId" ,id);
		}



		//エラーがあれば入力画面へ戻し、再入力を促す
		if(0 < errCnt){
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}

		//where条件をparamsにセット
		ArrayList<String> params = new ArrayList<String>();
		params.add(id);
		GoodsDAO sDao = new GoodsDAO();

		//登録済みの場合はエラーなのでメッセージ表示
		if(0 == sDao.kensuCheck(params)){
			session.setAttribute("msg", "商品がありません");
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}




		//excuteUpdateの戻り値が１以下なら登録失敗なのでメッセージ表示
		if(sDao.delete(id) < 1){
			session.setAttribute("msg", "削除できませんでした");
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}

		//この処理まで流れたということは追加成功なので、Serv05Result.jspへ遷移
		session.setAttribute("resTitle" , "削除処理(Serv06)");
		response.sendRedirect(request.getContextPath() + "/JSP/Serv06Result.jsp");
	}
}
