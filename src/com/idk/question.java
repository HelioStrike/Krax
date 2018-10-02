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
 * Servlet implementation class question
 */
@WebServlet("/question")
public class question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public question() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from questions where id = \'" + id + "\'");  
			
			if(rs.next()) {
				request.setAttribute("id", rs.getString(1));
				request.setAttribute("asker", rs.getString(2));
				request.setAttribute("title", rs.getString(3));
				request.setAttribute("body", rs.getString(4));
				request.setAttribute("dt_created", rs.getDate(5).toString());

				rs=stmt.executeQuery("select count(1) from answers where qid = \'" + id + "\'"); rs.next();
				int count = rs.getInt(1); 
				request.setAttribute("acount", count);

				if(count > 0)
				{
					rs=stmt.executeQuery("select * from answers where qid = \'" + id + "\'");  
					String authors[] = new String[count];
					String  bodies[] = new String[count];
					String dates[] = new String[count];
					
					count = 0;
					while(rs.next())
					{
						authors[count] = rs.getString(2);
						bodies[count] = rs.getString(3);
						dates[count] = rs.getDate(4).toString();
						count++;
					}
					
					request.setAttribute("authors", authors);
					request.setAttribute("bodies", bodies);
					request.setAttribute("dates", dates);				
				}
				
			    request.getRequestDispatcher("question.jsp").forward(request,response);
			} else { 
			    response.sendRedirect("/home");;
			}
     
			con.close();  			
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
