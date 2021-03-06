<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
<title><%= request.getAttribute("username") %> - Krax</title>
<style>

	#maindiv {
		margin:15vh;
		font-size: 20px;
	}

	.qbody {
		font-size: 18px;
		margin:20px;
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
		
		<img class="ui medium circular image" src="http://localhost:8080/krax/profpics/<%= request.getAttribute("profpic") %>" style="height:300px; width: 300px;">
	
		<% if(request.getAttribute("username").equals(request.getSession().getAttribute("username"))) { %>
		
		<form class="ui form" action="uploadprofpic" enctype="multipart/form-data" method="post">
           <div class="field" style="width:15vw;">
             <label>Change picture:</label>
             <input type="file" name="filename">
           </div>
           <div>
             <button class="ui button" type="submit">Submit</button>
           </div>
        </form>
	
		<% } %>
		
		<%
		
		if(request.getAttribute("fullname") != null)
		{
			out.println("<div class=\"ui massive header\">Username:</div> <p>" + request.getAttribute("username") + "</p>");
			out.println("<div class=\"ui massive header\">Fullname:</div> <p>" + request.getAttribute("fullname") + "</p>");
			out.println("<div class=\"ui massive header\">Email:</div> <p>" + request.getAttribute("email") + "</p>");
		}
		
		%>	
		
		<%
		
		int askedIds[] = (int[]) request.getAttribute("askedIds");
		String askedTitles[] = (String[]) request.getAttribute("askedTitles");
		String askedBodies[] = (String[]) request.getAttribute("askedBodies");
		String askedDates[] = (String[]) request.getAttribute("askedDates");
	
		%>
		
		<div class="ui massive header">Recent Questions:</div>
		
		<% for(int i = 0; i < Integer.parseInt(request.getAttribute("qcount").toString()); i++) { %>
		
		<div class="ui segment">
			<div class="ui huge header"><a href="<%= request.getContextPath() %>/question?id=<%= askedIds[i] %>"><%= askedTitles[i] %></a></div>
			<div class="qbody"><%= askedBodies[i] %></div>
			<br/>
			<div class="ui right floated"><b>Asked on:</b> <%= askedDates[i] %></div>
		</div>
		
		<% } %>
		
		<%
		
		int qids[] = (int[]) request.getAttribute("qids");
		String questions[] = (String[]) request.getAttribute("questions");
		String bodies[] = (String[]) request.getAttribute("bodies");
		String dates[] = (String[]) request.getAttribute("dates");
	
		%>
		
		<div class="ui massive header">Recent Answers:</div>
		
		<% for(int i = 0; i < Integer.parseInt(request.getAttribute("acount").toString()); i++) { %>
		
		<div class="ui segment">
			<div class="ui huge header"><a href="<%= request.getContextPath() %>/question?id=<%= qids[i] %>"><%= questions[i] %></a></div>
			<div class="qbody"><%= bodies[i] %></div>
			<br/>
			<div class="ui right floated"><b>Answered on:</b> <%= dates[i] %></div>
		</div>
		
		<% } %>
	</div>


</body>
</html>