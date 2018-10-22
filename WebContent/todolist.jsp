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
				<tr>
					<td>${list.id}</td>
					<td>${list.description}</td>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

