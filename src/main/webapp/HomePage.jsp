<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDo Application</title>
<style>
button {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  margin: 0 10px;
  width: 200px;
  padding: 10px 0;
  background-color: rgb(31, 81, 255);
  color: white;
  padding:3px;
  border-radius: 6px;
}

</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<jsp:include page="./common/header.jsp"></jsp:include>
<div class="container">
<p><center><h1>WELCOME!</h1></center></p>
<br>
<p><center><h2>This Is A TODO Application</h2></center></p>
<br>
<p><center><h3>If you are a new user register yourself by going to sign up page</h2></center></p>
<p><center><h3>If you have an account go to login page</h3></center></p>
<br>
<div><center>
<a href="register/register.jsp"><button class="reg">Sign up</button></a>
<a href="login/login.jsp"><button class="log">Login</button></a></center></div>
</div>
</body>
</html>