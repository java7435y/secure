package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import baseConst.SyouhinDefine;
import beans.GoodsDTO;
import dao.GoodsDAO;

public class Serv04_2 extends HttpServlet {

	private static final long serialVersionUID = 2161246420339085549L;

	public void doGet(HttpServletRequest request , HttpServletResponse response)
				throws ServletException , IOException{


		String id = request.getParameter("id");
		if(id == null){
			response.sendRedirect(request.getContextPath()+"/HTML/Serv04.html");
			return;
		}
		ArrayList<String> params = new ArrayList<String>();
		params.add(id);

		GoodsDAO sDao = new GoodsDAO();
		ArrayList<GoodsDTO> SyouhinData = sDao.select(params);
		request.setAttribute("db", SyouhinData);
		request.setAttribute("title", "商品の詳細(Serv04)");
		request.setAttribute("midashi" , "商品の詳細");
		request.setAttribute("itemHeader" , SyouhinDefine.HEAD_ALL);
		RequestDispatcher dispatch =
				request.getRequestDispatcher("JSP/Serv04_2Jstl.jsp");
		dispatch.forward(request, response);
	}

}
