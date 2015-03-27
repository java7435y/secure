package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GoodsDTO;
import dao.GoodsDAO;

public class Serv07 extends HttpServlet {

	private static final long serialVersionUID = 4470601410890705806L;

	/**
	 * Serv07のサーブレットに直接アクセスされた場合に入力画面へ戻すため、
	 * doGetにsendRedirectを記述
	 */
	public void doGet(HttpServletRequest request , HttpServletResponse response)
	throws ServletException , IOException{
		response.sendRedirect(request.getContextPath()+"/JSP/Serv07Jstl.jsp");
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
		String iniUrl = "/JSP/Serv07Jstl.jsp";

		//idが取得できない（null）ということは不正アクセスの可能性があるので
		// sendRedirectを記述し、入力画面へ戻す
		String id = request.getParameter("id");
		if(id == null){
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}




		String name = request.getParameter("name");
		String group = request.getParameter("group");
		String cost = request.getParameter("cost");
		String wholesale = request.getParameter("wholesale");


		//入力チｪック
		if(id.length() == 0){
			err_msg = "商品IDを入力してください";
			errCnt ++;
			session.setAttribute("msgId_err" , err_msg);
		}else{
			session.setAttribute("msgId" ,id);
		}

		if(name.length() == 0){
////			err_msg = "商品名を入力してください";
////			errCnt ++;
//			session.setAttribute("msgName_err" , err_msg);
		}else{
			session.setAttribute("msgName" , name);
		}

		if(group.length() == 0){
//			err_msg = "グループ名を入力してください";
//			errCnt ++;
//			session.setAttribute("msgGroup_err" , err_msg);
		}else{
			session.setAttribute("msgGroup" , group);
		}

		if(cost.length() == 0){
			cost = null;
		}else{
			try{
				Integer.parseInt(cost);
			}catch(Exception e){
				err_msg = "正しく数値を入力してください";
				errCnt ++;
				session.setAttribute("msgCost_err" , err_msg);
			}
			session.setAttribute("msgCost" , cost);
		}

		if(wholesale.length() == 0){
			wholesale = null;
		}else{
			try{
				Integer.parseInt(wholesale);
			}catch(Exception e){
				err_msg = "正しく数値を入力してください";
				errCnt ++;
				session.setAttribute("msgWholesale_err" , err_msg);
			}
			session.setAttribute("msgWholesale" , wholesale);
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

		//追加情報をGoodsDTO（beans）にセット
		GoodsDTO sData = new GoodsDTO();
		sData.setGoodsId(id);

		if(name != "") sData.setGoodsName(name);
		if(group != "") sData.setGoodsGroupName(group);
		if(cost!= "") sData.setCost(cost);
		if(wholesale != "") sData.setWholesale(wholesale);


		//excuteUpdateの戻り値が１以下なら登録失敗なのでメッセージ表示
		if(sDao.update(sData) < 1){
			session.setAttribute("msg", "更新できませんでした");
			response.sendRedirect(request.getContextPath() + iniUrl);
			return;
		}

		//この処理まで流れたということは追加成功なので、Serv05Result.jspへ遷移
		session.setAttribute("resTitle" , "更新(Serv07)");
		response.sendRedirect(request.getContextPath() + "/JSP/Serv07Result.jsp");
	}
}
