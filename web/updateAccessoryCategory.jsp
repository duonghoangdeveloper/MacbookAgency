<%-- 
    Document   : updateAccessoryCategory
    Created on : Oct 11, 2019, 9:09:35 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/accessoryCategoryListDocumentFragment.jsp" %>
<%@include file="fragment/accessoryCategoryDocumentFragment.jsp" %>

<c:set var="txtName" value="${param.txtName}"/>
<c:set var="txtType" value="${param.txtType}"/>
<c:set var="txtYear" value="${param.txtYear}"/>
<c:set var="txtSsd" value="${param.txtSsd}"/>
<c:set var="txtScreenSize" value="${param.txtScreenSize}"/>
<c:set var="txtTouchbar" value="${param.txtTouchbar}"/>
<c:set var="txtThumbnail" value="${param.txtThumbnail}"/>

<c:choose>
    <c:when test="${txtCategory}">
        <x:set var="txtCategory" select="$accessoryCategoryDocument/category"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtCategory" select="$txtCategory"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtType == null}">
        <x:set var="txtType" select="$accessoryCategoryDocument/type"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtType" select="$txtType"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtYear == null}">
        <x:set var="txtYear" select="$accessoryCategoryDocument/year"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtYear" select="$txtYear"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtSsd == null}">
        <x:set var="txtSsd" select="$accessoryCategoryDocument/ssd"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtSsd" select="$txtSsd"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtScreenSize == null}">
        <x:set var="txtScreenSize" select="$accessoryCategoryDocument/screenSize"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtScreenSize" select="$txtScreenSize"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtTouchbar == null}">
        <x:set var="txtTouchbar" select="$accessoryCategoryDocument/touchbar"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtTouchbar" select="$txtTouchbar"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtThumbnail == null}">
        <x:set var="txtThumbnail" select="$accessoryCategoryDocument/thumbnail"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtThumbnail" select="$txtThumbnail"/>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Update Accessory Category</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="accessoryCategories"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">
                <h1>Update Accessory Category</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Model ID:</label>
                        <span class="invalid-message">${requestScope.ACCESSORY_CATEGORY_ERROR_DTO.categoryErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtCategory" />"
                           disabled/>

                    <div class="input-label">
                        <label>Thumbnail:</label>
                        <span class="invalid-message">${requestScope.ACCESSORY_CATEGORY_ERROR_DTO.thumbnailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtThumbnail"
                           value="<x:out select="$txtThumbnail" />"
                           placeholder="Please enter thumbnail"/>

                    <input type="hidden" name="action" value="UpdateAccessoryCategory"/>
                    <input type="hidden" name="txtCategory" value="<x:out select="$txtCategory" />"/>
                    <button type="submit" style="margin-right: 5px;">Update</button>
                    <button type="button" onclick="location.replace('accessoryCategories.jsp');">Back</button>
                </form>
                <h1 style="margin-top: 30px;">Keywords To Crawl</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Keyword</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="accessoryCategoryKeywordDocument" select="$accessoryCategoryDocument/accessoryCategoryKeywordList/accessoryCategoryKeyword" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$accessoryCategoryKeywordDocument/keyword"/></td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteAccessoryCategoryKeyword"/>
                                        <input type="hidden" name="txtCategory" value="<x:out select="$accessoryCategoryDocument/category"/>"/>
                                        <input type="hidden" name="txtKeyword" value="<x:out select="$accessoryCategoryKeywordDocument/keyword"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <h1 style="margin-top: 30px;">Add New Keyword</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Keyword:</label>
                        <span class="invalid-message">${requestScope.ACCESSORY_CATEGORY_KEYWORD_ERROR_DTO.keywordErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtKeyword"
                           value="${param.txtKeyword}"
                           placeholder="Please enter keyword"/>

                    <input type="hidden" name="action" value="CreateAccessoryCategoryKeyword"/>
                    <input type="hidden" name="txtCategory" value="<x:out select="$accessoryCategoryDocument/category"/>"/>
                    <button type="submit" style="margin-right: 5px;">Add</button>
                </form>
            </section>
        </main>
    </body>
</html>
