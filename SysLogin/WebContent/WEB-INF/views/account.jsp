<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<body>
<h1>Create Account</h1>
<form action="/SysLogin/account/makeaccount" method="post">	
	<table>
        <tr><td>Email Address</td><td><input type="text" name="username"><br/></td></tr>
        <tr><td>Password</td><td><input type="password" name="password"></td></tr> 
        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
    </table>
</form>
</body>
</html>
