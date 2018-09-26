<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.css">
    <style>
      .body {
        height:100%;
        width:100%;
      }

      .wrapper {
        height:100%;
        width:100%;
      }
    </style>
  </head>
  <body>

    <div class="ui middle aligned grid wrapper">
      <div class="column">
        <div class="ui text container">
          <div class="ui segment">
            <div class="ui center aligned huge header">Login</div>
            <form class="ui form" action="login" method="post">
              <div class="field">
                <label>UserName</label>
                <input type="text" name="username" placeholder="UserName">
              </div>
              <div class="field">
                <label>Password</label>
                <input type="text" name="password" placeholder="Password">
              </div>
              <div class="ui center aligned container">
                <button class="ui button" type="submit">Submit</button>
              </div>
            </form>
          </div>
        </div>
        <div class="ui center aligned container" style="margin-top:3vh;">
        	<p>Not signed up yet?</p>
        	<a href="/Krax/register"><button class="ui button">Sign Up</button></a>
        </div>
      </div>
    </div>

  </body>
</html>
