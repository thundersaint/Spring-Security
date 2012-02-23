<!-- %@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %-->
<%@ page session="false" %>
<html>
    <head>
        <title>Home</title>
    </head>
    <body>
        <h1>Welcome Customer!</h1>
        <p><a href="/SysLogin/account/newaccount">Create Account</a></p>
        <p><a href="/SysLogin/req/myaccount">My Account</a></p>
        <p><a href="/SysLogin/login">Log in</a></p>
        <p><a href="/SysLogin/j_spring_security_logout">Log out</a></p>
    </body>
</html>