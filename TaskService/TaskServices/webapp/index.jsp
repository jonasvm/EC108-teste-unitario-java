<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Task Services</title>
</head>
<body>
	<h1>Task Services</h1>
	<p>This is a Web Service to handle a TODO List</p>

<h2>REST Services</h2>
<p>For now, go to <a href="<%=request.getRequestURL()%>api/task">
	<%=request.getRequestURL()%>api/task</a> </p>

<h2>SOAP Services</h2>
<p>For now, go to <a href="<%=request.getRequestURL()%>services/TaskServices?wsdl">
	<%=request.getRequestURL()%>services/TaskServices?wsdl</a> </p>

<h2>JDBC_CONNECTION_STRING <%=System.getProperty("JDBC_CONNECTION_STRING")%></h2>

<h2>Task List </h2>
<sql:query var="rs" dataSource="jdbc/tasksdb">
SELECT id, done, description FROM TASKS
</sql:query>

<c:forEach var="task" items="${rs.rows}">
	<input type="checkbox"  <c:if test="${task.done}">checked="checked"</c:if> />
	${task.description}<br/>
</c:forEach>
</body>
</html>