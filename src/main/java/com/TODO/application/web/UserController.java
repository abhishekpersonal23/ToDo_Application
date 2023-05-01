package com.TODO.application.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.TODO.application.dao.UserDao;
import com.TODO.application.userJavaBean.User;
@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;  

	public void init(ServletConfig config) throws ServletException {
		 userDao=new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("register/register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		User user=new User();
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(userName);
		user.setPassword(password);
		
		try {
			int result=userDao.registerUser(user);
			if(result==1) {
				request.setAttribute("NOTIFICATION", "User Registered Successfully!");
			}
			else if(result==0) {
				request.setAttribute("NOTIFICATION", "username already taken, User Registration Unsuccesfull");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("register/register.jsp");
		dispatcher.forward(request, response);
	}

}
