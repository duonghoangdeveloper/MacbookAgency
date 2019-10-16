<%-- 
    Document   : updateDomain
    Created on : Oct 11, 2019, 9:09:35 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/domainListDocumentFragment.jsp" %>
<%@include file="fragment/domainDocumentFragment.jsp" %>

<c:set var="txtName" value="${param.txtName}"/>
<c:set var="txtName" value="${param.txtXslPath}"/>

<c:choose>
    <c:when test="${txtDomain}">
        <x:set var="txtDomain" select="$domainDocument/domain"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtDomain" select="$txtDomain"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtName == null}">
        <x:set var="txtName" select="$domainDocument/name"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtName" select="$txtName"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtXslPath == null}">
        <x:set var="txtXslPath" select="$domainDocument/xslPath"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtXslPath" select="$txtXslPath"/>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Update Domain</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="domains"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Update Domain</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Domain:</label>
                        <span class="invalid-message">${requestScope.DOMAIN_ERROR_DTO.domainErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtDomain" />"
                           disabled
                           placeholder="Please enter domain"/>

                    <div class="input-label">
                        <label>Name:</label>
                        <span class="invalid-message">${requestScope.DOMAIN_ERROR_DTO.nameErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtName"
                           value="<x:out select="$txtName" />"
                           placeholder="Please enter name"/>
                    
                    <div class="input-label">
                        <label>XSL Path:</label>
                        <span class="invalid-message">${requestScope.DOMAIN_ERROR_DTO.xslPathErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtXslPath"
                           value="<x:out select="$txtXslPath" />"
                           placeholder="Please enter XSL path"/>

                    <input type="hidden" name="action" value="UpdateDomain"/>
                    <input type="hidden" name="txtDomain" value="<x:out select="$txtDomain" />"/>
                    <button type="submit" style="margin-right: 5px;">Update</button>
                    <button type="button" onclick="location.replace('domains.jsp');">Back</button>
                </form>
                <h1 style="margin-top: 30px;">Paths To Crawl</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Path</th>
                            <th>URL</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="pageDocument" select="$domainDocument/pageList/page" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$pageDocument/path"/></td>
                                <td>
                                    <a href="<x:out select="$domainDocument/domain"/><x:out select="$pageDocument/path"/>" target="_blank">
                                        <x:out select="$domainDocument/domain"/><x:out select="$pageDocument/path"/>
                                    </a>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeletePage"/>
                                        <input type="hidden" name="txtDomain" value="<x:out select="$domainDocument/domain"/>"/>
                                        <input type="hidden" name="txtPath" value="<x:out select="$pageDocument/path"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <h1 style="margin-top: 30px;">Add New Path</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Path:</label>
                        <span class="invalid-message">${requestScope.PAGE_ERROR_DTO.pathErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtPath"
                           value="${param.txtPath}"
                           placeholder="Please enter path"/>

                    <input type="hidden" name="action" value="CreatePage"/>
                    <input type="hidden" name="txtDomain" value="<x:out select="$domainDocument/domain"/>"/>
                    <button type="submit" style="margin-right: 5px;">Add</button>
                </form>
            </section>
        </main>
    </body>
</html>
