package com.TODO.application.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.TODO.application.jdbcUtils.*;
import com.TODO.application.userJavaBean.*;
public class UserDao{
	public int registerUser(User user) throws ClassNotFoundException {
		String query="INSERT INTO users "+
	                 "(first_name,last_name,user_name,password) values"+
		             "(?,?,?,?);";
		int result=0;
	    try {
	    	Connection con=JDBCUtils.getConnection();
	    	PreparedStatement stmt=con.prepareStatement(query);
	    	stmt.setString(1, user.getFirstName());
	    	stmt.setString(2, user.getLastName());
	    	stmt.setString(3, user.getUsername());
	    	stmt.setString(4, user.getPassword());
	    	result=stmt.executeUpdate();
	    	stmt.close();
	    	con.close();
	    }catch(SQLException ee) {
	    	JDBCUtils.printSQLException(ee);
	    }         
		return result;		
	}
}
