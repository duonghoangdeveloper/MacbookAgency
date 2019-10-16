<%-- 
    Document   : updateMacbookModel
    Created on : Oct 11, 2019, 9:09:35 AM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/macbookModelListDocumentFragment.jsp" %>
<%@include file="fragment/macbookModelDocumentFragment.jsp" %>

<c:set var="txtName" value="${param.txtName}"/>
<c:set var="txtType" value="${param.txtType}"/>
<c:set var="txtYear" value="${param.txtYear}"/>
<c:set var="txtSsd" value="${param.txtSsd}"/>
<c:set var="txtScreenSize" value="${param.txtScreenSize}"/>
<c:set var="txtTouchbar" value="${param.txtTouchbar}"/>
<c:set var="txtThumbnail" value="${param.txtThumbnail}"/>

<c:choose>
    <c:when test="${txtModelID}">
        <x:set var="txtModelID" select="$macbookModelDocument/modelID"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtModelID" select="$txtModelID"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtType == null}">
        <x:set var="txtType" select="$macbookModelDocument/type"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtType" select="$txtType"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtYear == null}">
        <x:set var="txtYear" select="$macbookModelDocument/year"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtYear" select="$txtYear"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtSsd == null}">
        <x:set var="txtSsd" select="$macbookModelDocument/ssd"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtSsd" select="$txtSsd"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtScreenSize == null}">
        <x:set var="txtScreenSize" select="$macbookModelDocument/screenSize"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtScreenSize" select="$txtScreenSize"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtTouchbar == null}">
        <x:set var="txtTouchbar" select="$macbookModelDocument/touchbar"/>
    </c:when>
    <c:otherwise>
        <x:set var="txtTouchbar" select="$txtTouchbar"/>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${txtThumbnail == null}">
        <x:set var="txtThumbnail" select="$macbookModelDocument/thumbnail"/>
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
        <title>Update Macbook Model</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="macbookModels"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Update Macbook Model</h1>
                <form action="MainServlet" method="POST">
                    <div class="input-label">
                        <label>Model ID:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.modelIDErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtModelID" />"
                           disabled/>

                    <div class="input-label">
                        <label>Type:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.typeErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtType" />"
                           disabled/>
                    
                    <div class="input-label">
                        <label>Year:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.yearErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtYear" />"
                           disabled/>
                    
                    <div class="input-label">
                        <label>SSD:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.ssdErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtSsd" />"
                           disabled/>
                    
                    <div class="input-label">
                        <label>Screen Size:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.screenSizeErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtScreenSize" />"
                           disabled/>
                    
                    <div class="input-label">
                        <label>Touchbar:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.touchbarErrorMessage}</span>
                    </div>
                    <input type="text"
                           value="<x:out select="$txtTouchbar" />"
                           disabled/>
                    
                    <div class="input-label">
                        <label>Thumbnail:</label>
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_ERROR_DTO.thumbnailErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtThumbnail"
                           value="<x:out select="$txtThumbnail" />"
                           placeholder="Please enter thumbnail"/>

                    <input type="hidden" name="action" value="UpdateMacbookModel"/>
                    <input type="hidden" name="txtModelID" value="<x:out select="$txtModelID" />"/>
                    <button type="submit" style="margin-right: 5px;">Update</button>
                    <button type="button" onclick="location.replace('macbookModels.jsp');">Back</button>
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
                        <x:forEach var="macbookModelKeywordDocument" select="$macbookModelDocument/macbookModelKeywordList/macbookModelKeyword" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$macbookModelKeywordDocument/keyword"/></td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteMacbookModelKeyword"/>
                                        <input type="hidden" name="txtModelID" value="<x:out select="$macbookModelDocument/modelID"/>"/>
                                        <input type="hidden" name="txtKeyword" value="<x:out select="$macbookModelKeywordDocument/keyword"/>"/>
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
                        <span class="invalid-message">${requestScope.MACBOOK_MODEL_KEYWORD_ERROR_DTO.keywordErrorMessage}</span>
                    </div>
                    <input type="text"
                           name="txtKeyword"
                           value="${param.txtKeyword}"
                           placeholder="Please enter keyword"/>

                    <input type="hidden" name="action" value="CreateMacbookModelKeyword"/>
                    <input type="hidden" name="txtModelID" value="<x:out select="$macbookModelDocument/modelID"/>"/>
                    <button type="submit" style="margin-right: 5px;">Add</button>
                </form>
            </section>
        </main>
    </body>
</html>
