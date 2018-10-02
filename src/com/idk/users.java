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
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  

			String username = request.getParameter("username");
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users where username = \'" + username + "\'");  
			
			if(rs.next())
			{
				request.setAttribute("fullname", rs.getString(1));
				request.setAttribute("email", rs.getString(2));
				request.setAttribute("username", rs.getString(3));
				request.setAttribute("profpic", rs.getString(5));
				
				int count = 5; 

				if(count > 0)
				{
					rs=stmt.executeQuery("select * from answers where author = \'" + username + "\' order by dt_answered desc limit 0," + count);  
					int qids[] = new int[count];
					String  bodies[] = new String[count];
					String dates[] = new String[count];
					
					count = 0;
					while(rs.next())
					{
						qids[count] = rs.getInt(1);
						bodies[count] = rs.getString(3);
						dates[count] = rs.getDate(4).toString();
						count++;
					}

					request.setAttribute("acount", count);
					String questions[] = new String[count];

					for(int i = 0; i < count; i++)
					{
						System.out.println(qids[i]);
						System.out.println(bodies[i]);
						System.out.println(dates[i]);
					}
					
					for(int i = 0; i < count; i++)
					{
						rs=stmt.executeQuery("select * from questions where id = \'" + qids[i] + "\'");  
						rs.next();
						questions[i] = rs.getString(3);
					}

					request.setAttribute("qids", qids);
					request.setAttribute("questions", questions);
					request.setAttribute("bodies", bodies);
					request.setAttribute("dates", dates);				
				}
				
				request.getRequestDispatcher("users.jsp").forward(request,response);

			}
			else
			{
				response.sendRedirect("error.jsp");
			}
					
			con.close();
		}
		catch (Exception e) {
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