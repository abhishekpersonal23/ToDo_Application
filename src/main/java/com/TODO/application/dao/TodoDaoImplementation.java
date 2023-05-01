package com.TODO.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.TODO.application.jdbcUtils.JDBCUtils;
import com.TODO.application.userJavaBean.Todo;

public class TodoDaoImplementation implements TodoDao {
	Connection con;
	 public TodoDaoImplementation() {
		 con=JDBCUtils.getConnection();
	 }
	
	private static final String query1="INSERT INTO todos"
			+"(title,username,description,target_date,is_done)"+
			"VALUES (?,?,?,?,?);";
	private static final String query2="SELECT id,title,username,description,target_date,is_done"+
			" FROM todos WHERE id=?;";
	private static final String query3="SELECT * FROM todos";
	
	private static final String query4="DELETE FROM todos WHERE id=?;";

	private static final String query5="UPDATE todos SET title=?, username=?, description=?, target_date=?, is_done=? WHERE id=?;";
	@Override
	public void insertTodo(Todo todoObj) throws SQLException {
			PreparedStatement stmt=con.prepareStatement(query1);
			stmt.setString(1, todoObj.getTitle());
			stmt.setString(2, todoObj.getUsername());
			stmt.setString(3, todoObj.getDescription());
			stmt.setDate(4, JDBCUtils.getSQLDate(todoObj.getTargetDate()));
			stmt.setBoolean(5, todoObj.getStatus());
			stmt.executeUpdate();
			stmt.close();
	}

	@Override
	public Todo selectTodo(long todoId) {
		Todo todo=null;
		try {
			PreparedStatement stmt=con.prepareStatement(query2);
			stmt.setLong(1, todoId);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				long id=rs.getLong("id");
				String username=rs.getString("username");
				String title=rs.getString("title");
				String description=rs.getString("description");
				LocalDate target_date=JDBCUtils.getUtilDate(rs.getDate("target_date")); 
				Boolean is_done=rs.getBoolean("is_done");
				todo=new Todo(id,title,username,description,target_date,is_done);
			}
			stmt.close();
		}
		catch(SQLException ee){
			JDBCUtils.printSQLException(ee);
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {
		ArrayList<Todo> allTodos=new ArrayList<>();
		try {
			PreparedStatement stmt=con.prepareStatement(query3);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				long id=rs.getLong("id");
				String username=rs.getString("username");
				String title=rs.getString("title");
				String description=rs.getString("description");
				LocalDate target_date=JDBCUtils.getUtilDate(rs.getDate("target_date")); 
				Boolean is_done=rs.getBoolean("is_done");
				allTodos.add(new Todo(id,username,title,description,target_date,is_done));
			}
			stmt.close();
		}
		catch(SQLException ee) {
			JDBCUtils.printSQLException(ee);
		}
		
		return allTodos;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted = false;
		try {
			PreparedStatement stmt=con.prepareStatement(query4);
			stmt.setInt(1, id);
			rowDeleted=stmt.executeUpdate()>0;
			stmt.close();
		}
		catch(SQLException ee) {
			JDBCUtils.printSQLException(ee);
		}
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated=false;
		try {
		PreparedStatement stmt=con.prepareStatement(query5);
		stmt.setString(1, todo.getTitle());
		stmt.setString(2, todo.getUsername());
		stmt.setString(3, todo.getDescription());
		stmt.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
		stmt.setBoolean(5, todo.getStatus());
		stmt.setLong(6, todo.getId());
		rowUpdated=stmt.executeUpdate()>0;
		stmt.close();
		}
		catch(SQLException ee) {
			JDBCUtils.printSQLException(ee);
		}
		return rowUpdated;
	}

}
