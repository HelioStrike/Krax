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
		margin-top:15vh;
		font-size: 20px;
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

	<div class="ui container" id="maindiv">
		
		<img src="<c:url value='/profpics/<%= request.getAttribute("profpic").toString() %>'/>">
	
		<%
		
		if(request.getAttribute("fullname") != null)
		{
			out.println("<div class=\"ui massive header\">Username:</div> <p>" + request.getAttribute("username") + "</p>");
			out.println("<div class=\"ui massive header\">Fullname:</div> <p>" + request.getAttribute("fullname") + "</p>");
			out.println("<div class=\"ui massive header\">Email:</div> <p>" + request.getAttribute("email") + "</p>");
		}
		
		%>	
	</div>


</body>
</html>