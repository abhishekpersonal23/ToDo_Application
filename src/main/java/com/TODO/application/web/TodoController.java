package com.TODO.application.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.TODO.application.dao.TodoDao;
import com.TODO.application.dao.TodoDaoImplementation;
import com.TODO.application.userJavaBean.Todo;
@WebServlet("/")
public class TodoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private TodoDao todoDao;
    public void init() {
    	todoDao=new TodoDaoImplementation();
    }
    
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {   
    	doGet(req,res);
    }
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
    	String action=request.getServletPath();
    	System.out.println(action);
            switch (action) {
            case "/new":
                try {
					showNewForm(request, response);
				} catch (ServletException | IOException | SQLException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
                break;
            case "/insert":
                try {
					insertTodo(request, response);
				} catch (ServletException | IOException | SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
                break;
            case "/delete":
                try {
					deleteTodo(request, response);
				} catch (ServletException | IOException | SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
                break;
            case "/edit":
                try {
					showEditForm(request, response);
				} catch (ServletException | IOException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
                break;
            case "/update":
                try {
					updateTodo(request, response);
				} catch (ServletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                break;
            case "/list":
                try {
					listTodo(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
                dispatcher.forward(request, response);
            } 
    }
    
    private void insertTodo(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	String title=req.getParameter("title");
    	String username=req.getParameter("username");
    	String description=req.getParameter("description");
    	boolean is_done=Boolean.valueOf(req.getParameter("is_done"));
    	Todo newTodo=new Todo(title,username,description,LocalDate.now(),is_done);
    	todoDao.insertTodo(newTodo);
    	res.sendRedirect("list");
    }
    private void listTodo(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	List<Todo> allTodos=todoDao.selectAllTodos();
    	req.setAttribute("listtodo", allTodos);
    	RequestDispatcher dispatcher=req.getRequestDispatcher("TODO/todo-list.jsp");
    	dispatcher.forward(req,res);
    }
    private void updateTodo(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	int id=Integer.valueOf(req.getParameter("id"));
    	String title = req.getParameter("title");
        String username = req.getParameter("username");
        String description = req.getParameter("description");
        LocalDate targetDate = LocalDate.parse(req.getParameter("targetDate"));
        boolean is_done = Boolean.valueOf(req.getParameter("is_done"));
        Todo updateTodo=new Todo(id,title,username,description,targetDate,is_done);
        todoDao.updateTodo(updateTodo);
        res.sendRedirect("list");
    }
    private void deleteTodo(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	int id=Integer.valueOf(req.getParameter("id"));
    	todoDao.deleteTodo(id);
    	res.sendRedirect("list");
    }
    private void showNewForm(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException, SQLException
    {
    	RequestDispatcher dispatcher=req.getRequestDispatcher("TODO/todo-form.jsp");
    	dispatcher.forward(req, res);
    }
    private void showEditForm(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, SQLException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = todoDao.selectTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("TODO/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }
}
