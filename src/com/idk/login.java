package com.idk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
//@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null)
		{
		    request.getRequestDispatcher("/home").forward(request,response);		
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users where username = \'" + username + "\' and password = \'" + password + "\'");  
			
			if(rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("fullname", rs.getString(1));
				session.setAttribute("email", rs.getString(2));
				session.setAttribute("username", rs.getString(3));
				session.setAttribute("password", rs.getString(4));
				
			    response.sendRedirect("/Krax/home");
			} else { 
				request.setAttribute("status", 1);
			    request.getRequestDispatcher("/login").forward(request,response);
			}
     
			con.close();  			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}

}
