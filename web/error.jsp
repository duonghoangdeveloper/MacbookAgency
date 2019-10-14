<%-- 
    Document   : invalid
    Created on : Oct 10, 2019, 3:26:41 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Error Page</title>
    </head>
    <body>
        <h1>ERROR!</h1>
        <c:if test="${ERROR_MESSAGE != null}">
            <p>Message: ${ERROR_MESSAGE}</p>
        </c:if>
            <button type="button" onclick="history.back();">Back</button>
    </body>
</html>
