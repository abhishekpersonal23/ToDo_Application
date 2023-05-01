<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    
    <title>ToDo Application</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>
  
  <body>
    <jsp:include page="../common/header.jsp"></jsp:include>
    
    <div class="container" style="overflow: auto">
      <h1>Login Form</h1>
      
      <div class="getAlert" role="alert">
          <p>${NOTIFICATION}</p>
        </div>
        
      <form action="<%=request.getContextPath()%>/login" method="post">
        <div class="form-group">
          <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
        </div>
        
        <div class="form-group">
          <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
        </div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </div>
  </body>
</html>