<%-- 
    Document   : createDomain
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
        <title>Create Domain</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="domains"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">

                <h1>Create New Domain</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Domain:</label>
                        <span class="invalid-message">${requestScope.DOMAIN_ERROR_DTO.domainErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtDomain"
                           value="${param.txtDomain}"
                           placeholder="Please enter domain"/>

                    <div class="input-label">
                        <label>Name:</label>
                        <span class="invalid-message">${requestScope.DOMAIN_ERROR_DTO.nameErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtName"
                           value="${param.txtName}"
                           placeholder="Please enter name"/>

                    <input type="hidden" name="action" value="CreateDomain"/>
                    <button type="submit" style="margin-right: 5px;">Create</button>
                    <button type="button" onclick="location.replace('domains.jsp');">Back</button>
                </form>
            </section>
        </main>
    </body>
</html>
