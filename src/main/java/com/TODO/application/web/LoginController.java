package com.TODO.application.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

 
import com.TODO.application.dao.LoginDao;
import com.TODO.application.userJavaBean.UserLoginBean;
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDao loginDao;  

	public void init(ServletConfig config) throws ServletException {
		loginDao=new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		authenticate(request,response);
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		UserLoginBean userLoginBean=new UserLoginBean();
		userLoginBean.setUserName(userName);
		userLoginBean.setPassword(password);
		try {
			if(loginDao.validate(userLoginBean)) {
				RequestDispatcher dispatcher=request.getRequestDispatcher("TODO/todo-list.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher("login/login.jsp");
				request.setAttribute("NOTIFICATION", "Wrong Password Or user-name");
				dispatcher.forward(request, response);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
