<%-- 
    Document   : admins
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/adminListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Admins Page</title>
    </head>
    <body>
        <%@include file="component/dashboardHeader.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="admins"/>
            <%@include file="component/dashboardAside.jsp" %>
            <section class="dashboard-main">
                <h1>Admin List</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Fullname</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <x:forEach var="adminDocument" select="$adminListDocument/adminList/admin" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$adminDocument/username"/></td>
                                <td><x:out select="$adminDocument/fullname"/></td>
                                <td><x:out select="$adminDocument/email"/></td>
                                <td><x:out select="$adminDocument/phone"/></td>
                                <td>
                                    <form action="updateAdmin.jsp" method="POST">
                                        <button type="submit">Update</button>
                                        <input type="hidden" name="txtUsername" value="<x:out select="$adminDocument/username"/>"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="MainServlet" method="POST">
                                        <button type="submit">Delete</button>
                                        <input type="hidden" name="action" value="DeleteAdmin"/>
                                        <input type="hidden" name="txtUsername" value="<x:out select="$adminDocument/username"/>"/>
                                    </form>
                                </td>
                            </tr>
                        </x:forEach>
                    </tbody>
                </table>
                <button type="button" onclick="location.replace('createAdmin.jsp');">Create Admin</button>
                
            </section>
        </main>
    </body>
</html>
