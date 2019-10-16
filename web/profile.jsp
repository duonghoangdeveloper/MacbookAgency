<%-- 
    Document   : updateAdmin
    Created on : Oct 11, 2019, 9:09:35 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/adminListDocumentFragment.jsp" %>
           
<c:set var="txtUsername" value="${adminInfoDTO.username}"/>

<x:set var="adminDocument" select="$adminListDocument//admin[username=$txtUsername]" />
<x:if select="not($adminDocument)">
    <c:redirect url="admins.jsp"/>
</x:if>

<c:set var="txtFullname" value="${param.txtFullname}"/>
<c:set var="txtEmail" value="${param.txtEmail}"/>
<c:set var="txtPhone" value="${param.txtPhone}"/>
<c:set var="txtPassword" value="${param.txtPassword}"/>
<c:set var="txtConfirmPassword" value="${param.txtConfirmPassword}"/>

<c:choose>
    <c:when test="${txtFullname == null}">
        <x:set var="txtFullname" select="$adminDocument/fullname"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtFullname" select="$txtFullname"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtEmail == null}">
        <x:set var="txtEmail" select="$adminDocument/email"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtEmail" select="$txtEmail"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtPhone == null}">
        <x:set var="txtPhone" select="$adminDocument/phone"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtPhone" select="$txtPhone"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtPassword == null}">
        <x:set var="txtPassword" select="$adminDocument/password"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtPassword" select="$txtPassword"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtConfirmPassword == null}">
        <x:set var="txtConfirmPassword" select="''"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtConfirmPassword" select="$txtConfirmPassword"/>
    </c:otherwise>
</c:choose>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Your Profile</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="profile"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Your Profile</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Username:</label>
                        <span class="invalid-message">${ERROR_DTO.usernameErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtUsername" />"
                           disabled
                           placeholder="Please enter username"/>

                    <div class="input-label">
                        <label>Password:</label>
                        <span class="invalid-message">${ERROR_DTO.passwordErrorMessage}</span>
                    </div>
                    <input type="password"
                           name="txtPassword"
                           value="<x:out select="$txtPassword" />"
                           placeholder="Let this blank to not change"/>

                    <div class="input-label">
                        <label>Comfirm password:</label>
                        <span class="invalid-message">${ERROR_DTO.confirmPasswordErrorMessage}</span>
                    </div>
                    <input type="password"
                           name="txtConfirmPassword"
                           value="<x:out select="$txtConfirmPassword" />"
                           placeholder="Let this blank to not change"/>

                    <div class="input-label">
                        <label>Full Name:</label>
                        <span class="invalid-message">${ERROR_DTO.fullnameErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtFullname"
                           value="<x:out select="$txtFullname" />"
                           placeholder="Please enter full name"/>

                    <div class="input-label">
                        <label>Email:</label>
                        <span class="invalid-message">${ERROR_DTO.emailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtEmail"
                           value="<x:out select="$txtEmail" />"
                           placeholder="Please enter email"/>

                    <div class="input-label">
                        <label>Phone:</label>
                        <span class="invalid-message">${ERROR_DTO.phoneErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtPhone"
                           value="<x:out select="$txtPhone" />"
                           placeholder="Please enter phone"/>


                    <input type="hidden" name="action" value="UpdateAdmin"/>
                    <input type="hidden" name="txtUsername" value="<x:out select="$txtUsername" />"/>
                    <button type="submit">Update</button>
                    <button type="button" onclick="location.replace('admins.jsp');">Back</button>
                </form>
            </section>
        </main>
    </body>
</html>
