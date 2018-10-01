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
 * Servlet implementation class newq
 */
@WebServlet("/newq")
public class newq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("username") != null)
		{
			request.getRequestDispatcher("newq.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("/home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		//status 0 - success
		//status 1 - form not filled completely
		if(title.equals("")||body.equals(""))
		{
		    request.getRequestDispatcher("newq.jsp").forward(request,response);
		}
		else
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  
				
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from questions where title = \"" + title + "\"");
						
				if(rs.next())
				{
					response.sendRedirect("/Krax/newq");
				}
				else
				{
					String query = " insert into questions (id, asker, title, body)"
					        + " values (?, ?, ?, ?)";
				
					Statement countSt = con.createStatement();
					ResultSet countSet = countSt.executeQuery("select count(*) from questions");
					
					countSet.next();
					int qCount = countSet.getInt(1);
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt(1, qCount+1);
					preparedStmt.setString(2, request.getSession().getAttribute("username").toString());
					preparedStmt.setString(3, title);
					preparedStmt.setString(4, body);
		
					preparedStmt.execute();
				    request.getRequestDispatcher("newq.jsp").forward(request,response);
				}
				      
				con.close();  
			} catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("status", 0);
			    request.getRequestDispatcher("newq.jsp").forward(request,response);
			}
			
		}
	}

}
