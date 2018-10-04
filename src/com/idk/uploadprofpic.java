package com.idk;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class uploadprofpic
 */
@WebServlet("/uploadprofpic")
public class uploadprofpic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadprofpic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String filePath = "/home/krypt/ProgramFiles/apache-tomcat/apache-tomcat-8.5.34/webapps/krax/profpics/";  
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	      factory.setSizeThreshold(250*1024);
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      upload.setSizeMax(250*1024);
	      
	      String extension = ""; 
	    		  
	      try {
	    	  List fileitems = upload.parseRequest(new ServletRequestContext(request));
	    	  Iterator i = fileitems.iterator();
	    	  	    	  
	    	  while(i.hasNext())
	    	  {
	    		  FileItem fi = (FileItem)i.next();
	    		 
	    		  //System.out.println(fi.getName());
	    		  //String parts[] = fi.getName().split(".");
	    		  
	    		  String imageName = request.getSession().getAttribute("username") + ".png";
	    		  File file = new File(filePath,  imageName);
	    		  file.createNewFile();
	    		  fi.write(file);
	    		  
	  			  Class.forName("com.mysql.cj.jdbc.Driver");  
				  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krax?user=root","root","sunny123");  

				  Statement stmt=con.createStatement();  
				  stmt.executeUpdate("update users set profpic = \'" + imageName + "\' where username = \'" + request.getSession().getAttribute("username") + "\'");  
				  
				  con.close();
	    	  }
	    	  
	    	  response.sendRedirect(request.getContextPath() + "/users?username=" + request.getSession().getAttribute("username"));
	    	  
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  response.sendRedirect(request.getContextPath() + "/users?username=" + request.getSession().getAttribute("username"));
	      }
	}

}