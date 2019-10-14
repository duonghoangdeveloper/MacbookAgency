<header class="dashboard-header">
    <h1 class="dashboard-title">Admin Dashboard</h1>
    <span class="dashboard-welcome-message">Welcome back, ${adminInfoDTO.fullname}!</span>
    <form action="MainServlet" method="POST">
        <input type="submit" name="action" value="Logout"/>
    </form>
</header>