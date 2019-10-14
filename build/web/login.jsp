<%-- 
    Document   : index
    Created on : Oct 9, 2019, 1:45:00 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Login Page</title>
    </head>
    <body>
        <form action="MainServlet" method="POST">
            Username:<br/>
            <input type="text" name="txtUsername" value="${param.txtUsername}"/><br/>
            Password:<br/>
            <input type="password" name="txtPassword" value="${param.txtPassword}"/><br/>
            <input type="submit" name="action" value="Login"/>
            <input type="reset"/>
            <span style="color: red;">${requestScope.LOGIN_FAILED_MESSAGE}</span>
        </form>
        
        
    </body>
</html>
