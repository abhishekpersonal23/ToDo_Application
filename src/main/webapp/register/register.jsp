<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    
    <title>ToDo</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>
  
  <body>
    <jsp:include page="../common/header.jsp"></jsp:include>
    
    <div class="container">
      <h2>
        User Register Form
      </h2>
      
      <div class="RegistrationForm">
        <div class="getAlert" role="alert">
          <p>${NOTIFICATION}</p>
        </div>
        
        <form action="<%=request.getContextPath() %>/register" method="post">
          <div class="form-group">
            <label for="uname">First Name:</label> <input type="text" class="form-control" id="uname" placeholder="First Name" name="firstName" required>
          </div>

          <div class="form-group">
            <label for="uname">Last Name:</label> <input type="text" class="form-control" id="uname" placeholder="last Name" name="lastName" required>
          </div>

          <div class="form-group">
            <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="userName" required>
          </div>

          <div class="form-group">
            <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
          </div>

          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </body>
</html>