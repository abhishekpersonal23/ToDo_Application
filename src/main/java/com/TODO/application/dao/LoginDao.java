package com.TODO.application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.TODO.application.jdbcUtils.*;
import com.TODO.application.userJavaBean.UserLoginBean;
public class LoginDao {
  public boolean validate(UserLoginBean loginBean)throws ClassNotFoundException
  {
	  String query="SELECT * FROM users WHERE user_name=? and password=?;";
	  boolean status=false;
	  Class.forName("com.mysql.cj.jdbc.Driver");
	  try {
	  Connection con=JDBCUtils.getConnection();
	  PreparedStatement stmt=con.prepareStatement(query);
	  stmt.setString(1, loginBean.getUserName());
	  stmt.setString(2, loginBean.getPassword());
	  
	  ResultSet rs=stmt.executeQuery();
	  status=rs.next();
	  }catch(SQLException ee) {
		  JDBCUtils.printSQLException(ee);
	  }
	  return status;
  }
}
