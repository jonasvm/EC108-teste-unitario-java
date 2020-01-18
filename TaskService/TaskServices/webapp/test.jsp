<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TaskDAO Test</title>
</head>
<body>
	<h1>TaskDAO Test</h1>
	<p>This is a Web Service to handle a TODO List</p>
	
	<p>Task Created with id: ${requestScope.taskId}</p>
	
	<p>Task Retrieved: ${requestScope.task.description}</p>
	
	<h2>Task List from DAO</h2>
	
	<c:forEach var="task" items="${requestScope.taskList}">
	<table>
		<tr><th>ID</th><th>Done?</th><th>Description</th></tr>
		<tr><td>${task.id}</td><td>${task.done}</td><td>${task.description}</td></tr>
	</table>
			
	</c:forEach>

</body>
</html>