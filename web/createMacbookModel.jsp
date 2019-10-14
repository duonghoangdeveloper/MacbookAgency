<%-- 
    Document   : createMacbookModel
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
        <title>Create Macbook Model</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="macbookModels"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">

                <h1>Create New Macbook Model</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Type:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.typeErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtType"
                           value="${param.txtType}"
                           placeholder="Please enter type"/>

                    <div class="input-label">
                        <label>Year:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.yearErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtYear"
                           value="${param.txtYear}"
                           placeholder="Please enter year"/>

                    <div class="input-label">
                        <label>SSD:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.ssdErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtSsd"
                           value="${param.txtSsd}"
                           placeholder="Please enter ssd"/>

                    <div class="input-label">
                        <label>Screen Size:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.screenSizeErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtScreenSize"
                           value="${param.txtScreenSize}"
                           placeholder="Please enter screenSize"/>

                    <div class="input-label">
                        <label>Touchbar:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.touchbarErrorMessage}</span>
                    </div>
                    <input type='checkbox' value='true' name='txtTouchbar' ${"true".equals(param.txtTouchbar) ? "checked" : ""}>
                    
                    <div class="input-label">
                        <label>Thumbnail:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.thumbnailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtThumbnail"
                           value="${param.txtThumbnail}"
                           placeholder="Please enter thumbnail"/>

                    <input type="hidden" name="action" value="CreateMacbookModel"/>
                    <button type="submit" style="margin-right: 5px;">Create</button>
                    <button type="button" onclick="location.replace('macbookModels.jsp');">Back</button>
                </form>
            </section>
        </main>
    </body>
</html>
