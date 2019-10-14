<%-- 
    Document   : accessoryCategories
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/accessoryCategoryListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Accessory Categories Page</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="accessoryCategories"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">
                <h1>Accessory Category List & Keywords To Crawl Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Category</th>
                            <th>Thumbnail</th>
                            <th>Keywords</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="accessoryCategoryDocument" select="$accessoryCategoryListDocument/accessoryCategoryList/accessoryCategory" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$accessoryCategoryDocument/category"/></td>
                                <td><x:out select="$accessoryCategoryDocument/thumbnail"/></td>
                                <td>
                                    <div class="array-cell">
                                        <x:forEach var="accessoryCategoryKeywordDocument" select="$accessoryCategoryDocument/accessoryCategoryKeywordList/accessoryCategoryKeyword">
                                            <span><x:out select="$accessoryCategoryKeywordDocument/keyword"/></span>
                                        </x:forEach> 
                                    </div>
                                </td>
                                <td>
                                    <form action="updateAccessoryCategory.jsp" method="POST">
                                        <button type="submit">Update</button>
                                        <input type="hidden" name="txtCategory" value="<x:out select="$accessoryCategoryDocument/category"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteAccessoryCategory"/>
                                        <input type="hidden" name="txtCategory" value="<x:out select="$accessoryCategoryDocument/category"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <button type="button" onclick="location.replace('createAccessoryCategory.jsp');">Create Accessory Category</button>
                <!--                <form action="MainServlet" method="POST">
                                    <input type="submit" name="action" value="Crawl"/>
                                </form>-->
            </section>
        </main>
    </body>
</html>
