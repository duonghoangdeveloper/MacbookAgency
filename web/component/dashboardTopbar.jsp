<header class="topbar">
    <h1 class="dashboard-title">Admin Dashboard</h1>
    <span class="dashboard-welcome-message">Welcome back, ${adminInfoDTO.fullname}!</span>
    <button type="button" onclick="location.replace('macbook.jsp')" style="margin-right: 15px;">Home</button>
    <form action="MainServlet" method="POST">
        <input type="submit" name="action" value="Logout"/>
    </form>
</header>