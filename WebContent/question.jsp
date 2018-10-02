<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
<title><%= request.getAttribute("title") %> - Krax</title>
<style>

	#maindiv {
		margin-top:15vh;
	}

	.qbody {
		font-size: 18px;
		margin:20px;
	}

</style>

</head>
<body>
    <div class="ui massive secondary menu" id="indexnav">
      <div class="ui container">
      
        <a class="header item" href="/Krax/home">
          <p class="menu-btn" style="font-size:30px; margin: 10px;"><i class="angle double right icon"></i>Krax</p>
        </a>

        <div class="right menu">
          <%
          	if(request.getSession().getAttribute("username") != null)
          	{
                out.println("<a class=\"ui item\" href=\"/Krax/newq\">Ask New Question</a>");          		
                out.println("<a class=\"ui item\" href=\"/Krax/users?username=" + request.getSession().getAttribute("username") + "\">Profile</a>");
                out.println("<a class=\"ui item\" href=\"/Krax/logout\">Logout</a>");          		
			}
          	else
          	{
                out.println("<a class=\"ui item\" href=\"/Krax/login\">Login</a>");
                out.println("<a class=\"ui item\" href=\"/Krax/register\">Register</a>");
          	}
          %>
        </div>
      </div>
    </div>

	<%
		String authors[] = (String[]) request.getAttribute("authors");
		String bodies[] = (String[]) request.getAttribute("bodies");
		String dates[] = (String[]) request.getAttribute("dates");
	%>


	<div class="ui container" id="maindiv">
		<div class="ui segment">
			<div class="ui huge header"><a href="/Krax/question?id=<%= request.getAttribute("id") %>"><%= request.getAttribute("title") %></a></div>
			<div class="qbody"><%= request.getAttribute("body") %></div>
			<div class="ui right floated">-<a href="/Krax/users?username=<%= request.getAttribute("asker") %>"><%= request.getAttribute("asker") %></a></div>
			<br/>
			<div class="ui right floated"><b>Asked on:</b> <%= request.getAttribute("dt_created") %></div>
			<a href="/Krax/answer?id=<%= request.getAttribute("id") %>"><div class="ui button right floated" style="margin: 30px;">Answer</div></a>
		</div>
		
		<br/>
		<div class="ui huge header">Answers: </div>
		
		<% for(int i = 0; i < Integer.parseInt(request.getAttribute("acount").toString()); i++) { %>
		
		<div class="ui segment">
			<div class="qbody"><%= bodies[i] %></div>
			<div class="ui right floated">-<a href="/Krax/users?username=<%= authors[i] %>"><%= authors[i] %></a></div>
			<br/>
			<div class="ui right floated"><b>Answered on:</b> <%= dates[i] %></div>
		</div>
		
		<% } %>
		
	</div>


</body>
</html>