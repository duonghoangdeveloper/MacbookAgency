<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="home-navbar">
    <div class='home-navbar-item ${"macbook".equals(homeNavbarActiveItem) ? "active" : ""}'>
        <a href="macbook.jsp">Macbook</a>
    </div>
    <div class='home-navbar-item ${"accessory".equals(homeNavbarActiveItem) ? "active" : ""} home-navbar-dropdown'>
        <div>Accessory</div>
        <div class="home-navbar-dropdown-content">
            <c:forEach var="category" items="${categoryListDTO}">
                <a class="home-navbar-dropdown-content-item ${category.category.equals(param.category) ? "active" : ""}" href="accessory.jsp?category=${category.category}">${category.category}</a>
            </c:forEach>
        </div>
    </div>
</nav>