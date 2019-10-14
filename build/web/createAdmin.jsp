<%-- 
    Document   : updateAdmin
    Created on : Oct 11, 2019, 9:09:35 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Create Admin</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="admins"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">

                <h1>Create New Admin</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Username:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.usernameErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtUsername"
                           value="${param.txtUsername}"
                           placeholder="Please enter username"/>

                    <div class="input-label">
                        <label>Password:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.passwordErrorMessage}</span>
                    </div>
                    <input type="password"
                           name="txtPassword"
                           value="${param.txtPassword}"
                           placeholder="Please enter password"/>

                    <div class="input-label">
                        <label>Comfirm password:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.confirmPasswordErrorMessage}</span>
                    </div>
                    <input type="password"
                           name="txtConfirmPassword"
                           value="${param.txtConfirmPassword}"
                           placeholder="Please confirm password"/>

                    <div class="input-label">
                        <label>Full name:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.fullnameErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtFullname"
                           value="${param.txtFullname}"
                           placeholder="Please enter full name"/>

                    <div class="input-label">
                        <label>Email:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.emailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtEmail"
                           value="${param.txtEmail}"
                           placeholder="Please enter email"/>

                    <div class="input-label">
                        <label>Phone:</label>
                        <span class="invalid-message">${requestScope.ADMIN_ERROR_DTO.phoneErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtPhone"
                           value="${param.txtPhone}"
                           placeholder="Please enter phone"/>
                    <br/>

                    <input type="hidden" name="action" value="CreateAdmin"/>
                    <button type="submit" style="margin-right: 5px;">Create</button>
                    <button type="button" onclick="location.replace('admins.jsp');">Back</button>
                </form>
            </section>
        </main>
    </body>
</html>
