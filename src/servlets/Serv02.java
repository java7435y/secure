package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import baseConst.SyouhinDefine;
import beans.GoodsDTO;

import dao.GoodsDAO;


public class Serv02 extends HttpServlet {

	private static final long serialVersionUID = 5440990844726034644L;

	public void doGet(HttpServletRequest request , HttpServletResponse response)
			throws ServletException , IOException{

		GoodsDAO sDao = new GoodsDAO();
		ArrayList<GoodsDTO> SyouhinData = sDao.select();
		request.setAttribute("db", SyouhinData);
		request.setAttribute("title", "商品一覧(Serv02)");
		request.setAttribute("midashi" , "商品一覧");
		request.setAttribute("itemHeader" , SyouhinDefine.HEAD_ALL);
		RequestDispatcher dispatch =
				request.getRequestDispatcher("/JSP/Serv02Jstl.jsp");
		dispatch.forward(request, response);
	}
}
