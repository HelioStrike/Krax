<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
    <style>
      body {
        height:100%;
        width:100%;
        background: #ee0979;  /* fallback for old browsers */
        background: -webkit-linear-gradient(to bottom, #ff6a00, #ee0979);  /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to bottom, #ff6a00, #ee0979); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
      }

      .wrapper {
        height: 90%;
        width: 90%;
      }

      #midtxt {
        color: white;
        font-size: 50px;
      }

    </style>
  </head>
  <body>
    <div class="ui massive secondary menu" id="indexnav">
      <div class="ui container">
      
        <a class="header item" href="/Krax/home">
          <p class="menu-btn" style="color: white; font-size:30px; margin: 10px;"><i class="angle double right icon"></i>Krax</p>
        </a>

        <div class="right menu">
          <% if(request.getSession().getAttribute("username") != null) { %>
                <a class="ui item" href="/Krax/newq" style="color:white;">Ask New Question</a>          		
                <a class="ui item" href="/Krax/users?username=<%= request.getSession().getAttribute("username") %>" style="color:white;">Profile</a>
                <a class="ui item" href="/Krax/logout" style="color:white;">Logout</a>         		
		  <% } else { %>
                <a class="ui item" href="/Krax/login" style="color:white;">Login</a>
                <a class="ui item" href="/Krax/register" style="color:white;">Register</a>
          <% } %>
        </div>
      </div>
    </div>

    <div class="ui middle aligned grid wrapper">
      <div class="column">
        <div class="ui center aligned container">
          <div class="ui massive header" id="midtxt">
              Start asking.
          </div>
          <a href="/Krax/login"><div class="ui massive button inverted">Login</div></a>
          <a href="/Krax/register"><div class="ui massive button inverted">Register</div></a>
        </div>
      </div>
    </div>


  </body>
</html>
