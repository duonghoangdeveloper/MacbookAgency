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
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="domains"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">
                <h1>Domain List & Paths To Crawl Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Domain</th>
                            <th>Name</th>
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
                                <td><x:out select="$domainDocument/domain"/></td>
                                <td><x:out select="$domainDocument/name"/></td>
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
                                    <form action="MainServlet" method="POST">
                                        <input type="submit" name="action" value="Crawl"/>
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
                <!--                <form action="MainServlet" method="POST">
                                    <input type="submit" name="action" value="Crawl"/>
                                </form>-->
            </section>
        </main>
    </body>
</html>
