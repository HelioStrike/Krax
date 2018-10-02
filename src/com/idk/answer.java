package com.idk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class answer
 */
@WebServlet("/answer")
public class answer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getSession().setAttribute("qid", request.getParameter("id").toString());
		
		request.getRequestDispatcher("answer.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String qid = request.getSession().getAttribute("qid").toString();
		String author = request.getSession().getAttribute("username").toString();
		String body = request.getParameter("body");
		
		request.getSession().setAttribute("qid", null);
		//status 0 - success
		//status 1 - form not filled completely
		if(body.equals(""))
		{
		    response.sendRedirect(request.getContextPath() + "/question?id=" + qid);
		}
		else
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  
				
				
				String query = " insert into answers (qid, author, body)"
					        + " values (?, ?, ?)";
														
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, Integer.parseInt(qid));
				preparedStmt.setString(2, request.getSession().getAttribute("username").toString());
				preparedStmt.setString(3, body);
		
				preparedStmt.execute();
			    response.sendRedirect(request.getContextPath() + "/question?id=" + qid);
				
				      
				con.close();  
			} catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("status", 0);
			    response.sendRedirect(request.getContextPath() + "/question?id=" + qid);
			}
			
		}
	}

}
