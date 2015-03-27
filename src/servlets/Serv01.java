package servlets;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Serv01  extends HttpServlet{

	private static final long serialVersionUID = -2790907656667744323L;

	public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
    	String msg;
    	Connection db = null;
    	try{
    		Context context = new InitialContext();
    		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/db");
    		db = ds.getConnection();		//ＤＢへ接続
    		msg = "接続成功！";
    		db.close();			//ＤＢ切断
    	}catch(Exception e){
    		System.out.println(e);
    		msg = "接続失敗・・・";
    	}finally{
			try{
				if(db != null) {
					db.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
    	response.setContentType("text/html; charset=utf-8");
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
    	out.println("<head><title>Serv01 Oracleと接続</title></head>");
    	out.println("<body>");
    	out.println(msg);
    	out.println("</body>");
    	out.println("</html>");
   }
}
