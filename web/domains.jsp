<%-- 
    Document   : domains
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/domainListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Domains Page</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="domains"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Domain List & Paths To Crawl Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Domain</th>
                            <th>Name</th>
                            <th>XSL Path</th>
                            <th>Paths</th>
                            <th>Crawl</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="domainDocument" select="$domainListDocument/domainList/domain" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><a href="<x:out select="$domainDocument/domain"/>" target="_blank"><x:out select="$domainDocument/domain"/></a></td>
                                <td><x:out select="$domainDocument/name"/></td>
                                <td><a href="${pageContext.request.contextPath}<x:out select="$domainDocument/xslPath"/>" target="_blank"><x:out select="$domainDocument/xslPath"/><a/></td>
                                <td>
                                    <div class="array-cell">
                                        <x:forEach var="pageDocument" select="$domainDocument/pageList/page">
                                            <a href="<x:out select="$domainDocument/domain"/><x:out select="$pageDocument/path"/>" target="_blank">
                                                <x:out select="$pageDocument/path"/>
                                            </a> 
                                        </x:forEach> 
                                    </div>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST" onsubmit="sessionStorage.clear();">
                                        <button type="submit">Crawl</button>
                                        <input type="hidden" name="action" value="CrawlDomain"/>
                                        <input type="hidden" name="txtDomain" value="<x:out select="$domainDocument/domain"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="updateDomain.jsp" method="POST">
                                        <button type="submit">Update</button>
                                        <input type="hidden" name="txtDomain" value="<x:out select="$domainDocument/domain"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteDomain"/>
                                        <input type="hidden" name="txtDomain" value="<x:out select="$domainDocument/domain"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <button type="button" onclick="location.replace('createDomain.jsp');">Create Domain</button>

                <c:set var="crawlPageListDocument" value="${requestScope.CRAWL_PAGE_LIST_DOCUMENT}"/>
                <c:if test="${crawlPageListDocument != null}">
                    <h1 style="margin-top: 30px;">Crawl Result</h1>
                    <p>Aliases: C (created), U (updated), F (failed), I (invalidated), Um (unmatched).</p>
                    
                    <table>
                        <thead>
                            <tr>
                                <th colspan="3">Domain: <a href="<x:out select="$crawlPageListDocument/crawlPageList/crawlPage[1]/page/domain"/>" target="_blank"><x:out select="$crawlPageListDocument/crawlPageList/crawlPage[1]/page/domain"/></a></th>
                                <th colspan="5">Macbook</th>
                                <th colspan="5">Accessory</th>
                            </tr>
                            <tr>
                                <th>No</th>
                                <th>Time</th>
                                <th>Path</th>
                                <th>C</th>
                                <th>U</th>
                                <th>F</th>
                                <th>I</th>
                                <th>Um</th>
                                <th>C</th>
                                <th>U</th>
                                <th>F</th>
                                <th>I</th>
                                <th>Um</th>
                            </tr>
                        </thead>
                        <tbody>
                            <x:forEach var="crawlPageDocument" select="$crawlPageListDocument/crawlPageList/crawlPage" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td><x:out select="$crawlPageDocument/crawlAt"/></td>
                                    <td><a href="<x:out select="$crawlPageDocument/page"/>" target="_blank"><x:out select="$crawlPageDocument/page/path"/></td>
                                    <td><x:out select="count($crawlPageDocument/createdMacbookList/macbook)"/></td>
                                    <td><x:out select="count($crawlPageDocument/updatedMacbookList/macbook)"/></td>
                                    <td><x:out select="count($crawlPageDocument/failedMacbookList/macbook)"/></td>
                                    <td><x:out select="count($crawlPageDocument/invalidatedMacbookList/macbook)"/></td>
                                    <td><x:out select="count($crawlPageDocument/unmatchedMacbookList/macbook)"/></td>
                                    <td><x:out select="count($crawlPageDocument/createdAccessoryList/accessory)"/></td>
                                    <td><x:out select="count($crawlPageDocument/updatedAccessoryList/accessory)"/></td>
                                    <td><x:out select="count($crawlPageDocument/failedAccessoryList/accessory)"/></td>
                                    <td><x:out select="count($crawlPageDocument/invalidatedAccessoryList/accessory)"/></td>
                                    <td><x:out select="count($crawlPageDocument/unmatchedAccessoryList/accessory)"/></td>
                                </tr>
                            </x:forEach>
                        </tbody>
                    </table>
                </c:if>
            </section>
        </main>
    </body>
</html>
