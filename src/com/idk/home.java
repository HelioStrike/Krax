package com.idk;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("username") == null)
		{
			response.sendRedirect("index.jsp");
		}
		else
		{
			int count = 0;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  

				String start = request.getParameter("start");
				String dif = request.getParameter("diff");
				
				if(start == null || dif == null) { start = "0"; dif = "10";}
				
				int diff = Integer.parseInt(dif);
				
				if(diff > 20)
				{
					dif = Integer.toString(20);
					diff = 20;
				}
				
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("SELECT * FROM questions ORDER BY dt_created DESC LIMIT " + start + "," + dif);  
				
				
				int ids[] = new int[diff];
				String askers[] = new String[diff];
				String titles[] = new String[diff];
				String bodies[] = new String[diff];
				String dates[] = new String[diff];
				
				while(rs.next())
				{
					ids[count] = rs.getInt(1);
					askers[count] = rs.getString(2);
					titles[count] = rs.getString(3);
					bodies[count] = rs.getString(4);
					dates[count] = rs.getDate(5).toString();
					count++;
				}
				
				request.setAttribute("ids", ids);
				request.setAttribute("askers", askers);
				request.setAttribute("titles", titles);
				request.setAttribute("bodies", bodies);
				request.setAttribute("dates", dates);
	     
				con.close();  			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("count", count);
		    request.getRequestDispatcher("home.jsp").forward(request,response);
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
