package com.akeris.webtodolist.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteToDoServlet
 */
@WebServlet("/DeleteToDoServlet")
public class DeleteToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
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
		int id = Integer.parseInt(request.getParameter("todoId"));
		dbUtil.deleteTodo(id);
		response.sendRedirect("TodoListServlet");
	}

}
