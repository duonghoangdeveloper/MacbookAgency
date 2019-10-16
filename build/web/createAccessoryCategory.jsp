<%-- 
    Document   : createAccessoryCategory
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
        <title>Create Accessory Category</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="accessoryCategories"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">

                <h1>Create New Accessory Category</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Category:</label>
                        <span class="invalid-message">${requestScope.ACCESSORY_CATEGORY_ERROR_DTO.categoryErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtCategory"
                           value="${param.txtCategory}"
                           placeholder="Please enter category"/>

                    <div class="input-label">
                        <label>Thumbnail:</label>
                        <span class="invalid-message">${requestScope.ACCESSORY_CATEGORY_ERROR_DTO.thumbnailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtThumbnail"
                           value="${param.txtThumbnail}"
                           placeholder="Please enter thumbnail"/>

                    <input type="hidden" name="action" value="CreateAccessoryCategory"/>
                    <button type="submit" style="margin-right: 5px;">Create</button>
                    <button type="button" onclick="location.replace('accessoryCategories.jsp');">Back</button>
                </form>
            </section>
        </main>
    </body>
</html>
