<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Edit list item</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<form action="LoginServlet" method="get">
		<input class = "add-todo-button" type="submit" value="Log Out"/>
	</form>
	<h1>Edit list item</h1>
	<h1>Welcome <c:out value="${sessionScope.USER_NAME}"/></h1>
	<form action="EditToDoServlet" method = "post">
		<table class="blueTable">
			<tbody>
				<tr>
					<td><label>Description : </label> </td>
					<td><input type="text" name = "description" value="${Todo.description}"/></td>
				</tr>
				<tr>
					<td><label></label> </td>
					<td><input class = "add-todo-button" type="submit" value = "Save"/></td>
				</tr>
			</tbody>
		</table>
	</form>
<div style="clear:both;"></div>
	<a href="TodoListServlet">Back to List</a>
</body>
</html>

