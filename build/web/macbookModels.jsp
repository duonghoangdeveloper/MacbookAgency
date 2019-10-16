<%-- 
    Document   : macbookModels
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/macbookModelListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Macbook Models Page</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="macbookModels"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Macbook Model List & Keywords To Crawl Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Model ID</th>
                            <th>Type</th>
                            <th>Year</th>
                            <th>SSD</th>
                            <th>Screen Size</th>
                            <th>Touchbar</th>
                            <th>Thumbnail</th>
                            <th>Keywords</th>
                            <th>Products</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="macbookModelDocument" select="$macbookModelListDocument/macbookModelList/macbookModel" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$macbookModelDocument/modelID"/></td>
                                <td><x:out select="$macbookModelDocument/type"/></td>
                                <td><x:out select="$macbookModelDocument/year"/></td>
                                <td><x:out select="$macbookModelDocument/ssd"/></td>
                                <td><x:out select="$macbookModelDocument/screenSize"/></td>
                                <td><x:out select="$macbookModelDocument/touchbar"/></td>
                                <td><img src="<x:out select="$macbookModelDocument/thumbnail"/>" witdh="75" height="75"/></td>
                                <td>
                                    <div class="array-cell">
                                        <x:forEach var="macbookModelKeywordDocument" select="$macbookModelDocument/macbookModelKeywordList/macbookModelKeyword">
                                            <span><x:out select="$macbookModelKeywordDocument/keyword"/></span>
                                        </x:forEach> 
                                    </div>
                                </td>
                                <td>
                                    <form action="macbookModelProducts.jsp" method="POST">
                                        <button type="submit">Products</button>
                                        <input type="hidden" name="modelID" value="<x:out select="$macbookModelDocument/modelID"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="updateMacbookModel.jsp" method="POST">
                                        <button type="submit">Update</button>
                                        <input type="hidden" name="txtModelID" value="<x:out select="$macbookModelDocument/modelID"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteMacbookModel"/>
                                        <input type="hidden" name="txtModelID" value="<x:out select="$macbookModelDocument/modelID"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <button type="button" onclick="location.replace('createMacbookModel.jsp');">Create Macbook Model</button>
                <!--                <form action="MainServlet" method="POST">
                                    <input type="submit" name="action" value="Crawl"/>
                                </form>-->
            </section>
        </main>
    </body>
</html>
