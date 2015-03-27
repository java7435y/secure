package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import baseConst.SyouhinDefine;
import beans.GoodsDTO;
import dao.*;

public class Serv04_1 extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 7424159009025664272L;

	public void doGet(HttpServletRequest request , HttpServletResponse response)
			throws ServletException , IOException{

		request.setCharacterEncoding("utf-8");

		String sName = request.getParameter("name");
		if(sName == null){
			response.sendRedirect(request.getContextPath()+"/HTML/Serv04.html");
			return;
		}

		GoodsDAO sDao = new GoodsDAO();
		ArrayList<GoodsDTO> SyouhinData = sDao.selectLike(sName);
		request.setAttribute("db", SyouhinData);
		request.setAttribute("title", "入力値の検索結果(Serv04)");
		request.setAttribute("midashi" , "入力値の検索結果");
		request.setAttribute("itemHeader" , SyouhinDefine.HEAD_PARTS);
		RequestDispatcher dispatch =
			request.getRequestDispatcher("JSP/Serv04_1Jstl.jsp");
		dispatch.forward(request, response);
	}
}
