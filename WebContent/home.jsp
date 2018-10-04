<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
<title>Home - Krax</title>
<style>

	#maindiv {
		margin:15vh;
		font-size: 16px;
	}

	.qbody {
		font-size: 18px;
		margin: 20px;
	}

</style>
</head>
<body>
	<div class="ui massive inverted menu" id="indexnav">
      <div class="ui container">
      
        <a class="header item" href="<%= request.getContextPath() %>/home">
          <p class="menu-btn" style="font-size:30px; margin: 10px;"><i class="angle double right icon"></i>Krax</p>
        </a>

        <div class="right menu">
          <% if(request.getSession().getAttribute("username") != null) { %>
                <a class="ui item" href="<%= request.getContextPath() %>/newq">Ask New Question</a>          		
                <a class="ui item" href="<%= request.getContextPath() %>/users?username=<%= request.getSession().getAttribute("username") %>">Profile</a>
                <a class="ui item" href="<%= request.getContextPath() %>/logout">Logout</a>         		
		  <% } else { %>
                <a class="ui item" href="<%= request.getContextPath() %>/login">Login</a>
                <a class="ui item" href="<%= request.getContextPath() %>/register">Register</a>
          <% } %>
        </div>
      </div>
    </div>
	
	<div class="ui container" id="maindiv">
	<%
		int ids[] = (int[]) request.getAttribute("ids");
		String askers[] = (String[]) request.getAttribute("askers");
		String titles[] = (String[]) request.getAttribute("titles");
		String bodies[] = (String[]) request.getAttribute("bodies");
		String dates[] = (String[]) request.getAttribute("dates");

	%>
	
	<% 	for(int i = 0; i < Integer.parseInt(request.getAttribute("count").toString()); i++) { %>
			<div class="ui segment">
				<div class="ui huge header"><a href="<%= request.getContextPath() %>/question?id=<%= ids[i] %>"><%= titles[i] %></a></div>
				<p class="qbody"><%= bodies[i].substring(0, (bodies[i].length() > 50)? 50: bodies[i].length()) %>...</p>
				<p>-<a href="<%= request.getContextPath() %>/users?username=<%= askers[i] %>"><%= askers[i] %></a></p>
			</div>
	<% 	}	%>
		
	<a href="<%= request.getContextPath() %>/home?start=<%= (Integer.parseInt(request.getAttribute("start").toString()) - Integer.parseInt(request.getAttribute("diff").toString()))  %>&diff=<%= request.getAttribute("diff") %>"><div class="ui button">Next</div></a>
	<a href="<%= request.getContextPath() %>/home?start=<%= (Integer.parseInt(request.getAttribute("start").toString()) + Integer.parseInt(request.getAttribute("diff").toString()))  %>&diff=<%= request.getAttribute("diff") %>"><div class="ui button">Next</div></a>
		
	</div>
	
</body>
</html>