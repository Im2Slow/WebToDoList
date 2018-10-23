package com.akeris.webtodolist.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	@Resource (name="jdbc/webtodolist")
	private DataSource dataSource;
	HttpSession session;
	private static String connectionError = "Error : your username and/or password is not valid because it doesn't exist in our database";
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dbUtil = new DBUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = null;
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			User usr = dbUtil.Login(userName, password);
			if(usr == null) {
				//request.setAttribute("errorMsg", connectionError);
				response.sendRedirect("LoginServlet?message=" + connectionError);
			}
			else {
				session = request.getSession();
				session.setAttribute("USER_NAME", userName);
				session.setAttribute("ROLE", usr.getRole());
				//request.setAttribute("USER_NAME", userName);
				response.sendRedirect("TodoListServlet");
				return;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String getConnectionError() {
		return connectionError;
	}
}
