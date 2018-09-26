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
    <div class="ui massive inverted secondary menu" id="indexnav">
      <div class="ui container">
      
        <a class="header item" href="/">
          <p class="menu-btn" style="color: white; font-size:30px; margin: 10px;"><i class="angle double right icon"></i>Krux</p>
        </a>

        <div class="right menu">
          <a class="ui item" href="/Krax/login">Login</a>
          <a class="ui item" href="/Krax/register">Register</a>
        </div>
      </div>
    </div>

    <div class="ui middle aligned grid wrapper">
      <div class="column">
        <div class="ui center aligned container">
          <div class="ui massive header" id="midtxt">
              Get Started.
          </div>
          <a href="/Krax/login"><div class="ui massive button inverted">Login</div></a>
          <a href="/Krax/register"><div class="ui massive button inverted">Register</div></a>
        </div>
      </div>
    </div>


  </body>
</html>
