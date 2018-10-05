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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		    response.sendRedirect(request.getContextPath() + "/home");		
		}
		else
		{
			request.setAttribute("status", 0);
			request.getRequestDispatcher("register.jsp").forward(request,  response);;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		request.setAttribute("status", 0);

		//status 0 - success
		//status 1 - form not filled completely
		//status 2 - an error has occurred
		//status 3 - username taken
		if(fullname.equals("")||email.equals("")||username.equals("")||password.equals("")||request.getParameter("tnc")==null||!password.equals(cpassword))
		{
			request.setAttribute("status", 1);
		    request.getRequestDispatcher("register.jsp").forward(request,response);
		}
		else
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  
				
				Statement st=con.createStatement();  
				ResultSet rs=st.executeQuery("select * from users where username = \'" + username + "\'");
				if(rs.next())
				{
					request.setAttribute("status", 3);
				    request.getRequestDispatcher("register.jsp").forward(request,response);
				}
				else
				{
					String query = " insert into users (fullname, email, username, password)"
					        + " values (?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, fullname);
					preparedStmt.setString(2, email);
					preparedStmt.setString(3, username);
					preparedStmt.setString(4, password);
		
					preparedStmt.execute();
					response.sendRedirect(request.getContextPath() + "/login");
				}
				      
				con.close();  
			} catch(Exception e) {
				e.printStackTrace();
				request.setAttribute("status", 2);
			    request.getRequestDispatcher("register.jsp").forward(request,response);
			}
			
		}
		
	}

}
