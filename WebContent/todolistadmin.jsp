<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<form action="LoginServlet" method="get">
		<input class = "add-todo-button" type="submit" value="Log Out"/>
	</form>
	<form action="AddToDoServlet" method="get">
		<input class = "add-todo-button" type="submit" value="Add to list"/>
	</form>
	<h1>Welcome <c:out value="${sessionScope.USER_NAME}"/></h1>
	<table class="blueTable">
		<thead>
			<tr>
				<th>Id</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${TODO_LIST }">
			<c:url var="EditLink" value= "EditToDoServlet">
				<c:param name="todoId" value="${list.id}"/>
			</c:url>
			<c:url var="DeleteLink" value= "DeleteToDoServlet">
				<c:param name="todoId" value="${list.id}"/>
			</c:url>
				<tr>
					<td>${list.id}</td>
					<td>${list.description}</td>
					<td> <a href="${EditLink }"> Edit</a>|<a href="${DeleteLink }">Delete</a></td>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

