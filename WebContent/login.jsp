<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Web ToDoList</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
	<form class="login" method="post" action="LoginServlet">
		<h1 class="login-title">WebToDoList Login</h1>
		<input name ="username" type="text" class="login-input" value="<c:out value="${sessionScope.USER_NAME}"/>" placeholder="Username" autofocus> 
		<input name = "password" type="password" class="login-input" placeholder="Password"> 
   		<p class = "error"><c:out value="${param.message}"/></p>
		<input type="submit" value="Log In" class="login-button">
	</form>
</body>
</html>