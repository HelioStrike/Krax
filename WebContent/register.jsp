<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
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
            <div class="ui center aligned huge header">Sign Up</div>
            <form class="ui form" action="register" method="post">
              <div class="field">
                <label>Full Name</label>
                <input type="text" name="fullname" placeholder="Full Name">
              </div>
              <div class="field">
                <label>Email</label>
                <input type="text" name="email" placeholder="Email">
              </div>
              <div class="field">
                <label>UserName</label>
                <input type="text" name="username" placeholder="UserName">
              </div>
              <div class="field">
                <label>Password</label>
                <input type="password" name="password" placeholder="Password">
              </div>
              <div class="field">
                <label>Re-enter Password</label>
                <input type="password" name="cpassword" placeholder="Re-enter Password">
              </div>
              <div class="ui center aligned container">
                <div class="field">
                  <div class="ui checkbox">
                    <input type="checkbox" name="tnc">
                    <label>I agree to the terms and conditions</label>
                  </div>
                </div>
                <button class="ui button" type="submit">Submit</button>
              </div>
            </form>
            
            <% if((int)request.getAttribute("status") == 1) { %>            
	        <div class="ui red info message">
				Fill up all boxes, check the T&C, and re-enter the password correctly!
			</div>
			<% } else if ((int)request.getAttribute("status") == 2) { %>
	        <div class="ui red info message">
			    An error has occurred.
			</div>
			<% } else if ((int)request.getAttribute("status") == 3) { %>
	        <div class="ui red info message">
			    Username taken!
			</div>
			<% } %>			
            
            
          </div>
        </div>
        <div class="ui center aligned container" style="margin-top:3vh;">
        	<p>Already registered?</p>
        	<a href="<%= request.getContextPath() %>/login"><button class="ui button">Sign In</button></a>
        </div>
      </div>
    </div>

  </body>
</html>