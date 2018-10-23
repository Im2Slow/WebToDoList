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
 * Servlet implementation class EditToDoServlet
 */
@WebServlet("/EditToDoServlet")
public class EditToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBUtil dbUtil;
	@Resource(name="jdbc/webtodolist")
	private DataSource dataSource;
	int id;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dbUtil = new DBUtil(dataSource);
	}
	public EditToDoServlet() {
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		id = Integer.parseInt(request.getParameter("todoId"));
		TodoList todo = dbUtil.fetchTodo(id);
		request.setAttribute("Todo", todo);
		request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String description = request.getParameter("description");
		TodoList todo = new TodoList(id,description);
		dbUtil.updateTodo(todo);
		response.sendRedirect("TodoListServlet");
	}

}
