package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import baseConst.SyouhinDefine;
import beans.GoodsDTO;
import dao.GoodsDAO;


public class Serv03 extends HttpServlet {

	private static final long serialVersionUID = -4529805666348606379L;

	public void doGet(HttpServletRequest request , HttpServletResponse response)
				throws ServletException , IOException{

		GoodsDAO sDao = new GoodsDAO();

		ArrayList<String> params = new ArrayList<String>();
		String id = request.getParameter("id");
		if(id == null){
			response.sendRedirect(request.getContextPath()+"/HTML/Serv03.html");
			return;
		}
		params.add(id);

		ArrayList<GoodsDTO> SyouhinData = sDao.select(params);
		request.setAttribute("db", SyouhinData);
		request.setAttribute("title", "検索結果(Serv03)");
		request.setAttribute("midashi" , "検索結果");
		request.setAttribute("itemHeader" , SyouhinDefine.HEAD_ALL);
		RequestDispatcher dispatch =
				request.getRequestDispatcher("/JSP/Serv03Jstl.jsp");
		dispatch.forward(request, response);
	}

}
