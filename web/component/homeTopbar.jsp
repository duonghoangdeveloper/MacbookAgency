<header class="topbar">
    <h1>Macbook Agency</h1>
    <c:if test="${empty sessionScope.ADMIN_INFO_DTO}">
        <div style="flex: 1; height: 100%;"></div>
        <button type="button" name="action" onclick="location.href = 'login.jsp';">Login</button>
    </c:if>
    <c:if test="${not empty sessionScope.ADMIN_INFO_DTO}">
        <div style="flex: 1; height: 100%;"></div>
        <span class="dashboard-welcome-message">Welcome back, ${sessionScope.ADMIN_INFO_DTO.fullname}!</span>
        <button type="button" onclick="location.replace('admins.jsp')" style="margin-right: 15px;">Dashboard</button>
        <form action="MainServlet" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
    </c:if>
</header>