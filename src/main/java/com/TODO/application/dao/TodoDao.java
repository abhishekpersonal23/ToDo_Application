package com.TODO.application.dao;
import java.sql.SQLException;
import java.util.List;
import com.TODO.application.userJavaBean.Todo;
public interface TodoDao {
   void insertTodo(Todo todoObj)throws SQLException;
   Todo selectTodo(long todoId);
   List<Todo> selectAllTodos();
   boolean deleteTodo(int id) throws SQLException;
   boolean updateTodo(Todo todo) throws SQLException;
}
