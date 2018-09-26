package com.idk;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class users
 */
@WebServlet("/users")
public class users extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test2?user=root","root","sunny123");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users");  
			
			int count = 0;
			while(rs.next())  
			{
				count++;
			}
			
			
			System.out.println(count);
			if(count == 0) { response.sendRedirect("users.jsp");}
			else
			{
				rs=stmt.executeQuery("select * from users");
				String strset[][] = new String[count][3];

				for(int i = 0; i < count; i++) {
					rs.next();
					for(int j = 0; j < 3; j++) {
						strset[i][j] = rs.getString(j+1);
					}
				}
				
				request.setAttribute("datalen", count);
				request.setAttribute("data", strset);
				
				con.close();  
				
			    request.getRequestDispatcher("users.jsp").forward(request,response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
