<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
    <style>
      .wrapper {
        height:90%;
        width:100%;
      }
    </style>
  </head>
  <body>

    <div class="ui massive inverted menu" id="indexnav">
      <div class="ui container">
      
        <a class="header item" href="/Krax/home">
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

    <div class="ui middle aligned grid wrapper">
      <div class="column">
        <div class="ui text container">
          <div class="ui segment">
            <div class="ui center aligned huge header">Answer</div>
            <form class="ui form" action="answer" method="post">
              <div class="field">
                <label>Answer</label>
                <textarea name="body" placeholder="Answer"></textarea>
              </div>
              <div class="ui center aligned container">
                <button class="ui button" type="submit">Submit</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
