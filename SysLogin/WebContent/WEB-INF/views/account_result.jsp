<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Result</title>
	</head>
	<body>
		<h1>Welcome Customer!</h1>
		<br/>
		<h1><c:out value="${output}"></c:out></h1>
		<p><a href="/SysLogin/">HOME</a></p>
	</body>
</html>