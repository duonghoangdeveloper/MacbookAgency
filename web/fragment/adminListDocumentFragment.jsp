<c:set var="adminListDocument" value="${sessionScope.ADMIN_LIST_DOCUMENT}"/>
<c:if test="${empty adminListDocument}">
    <c:redirect url="MainServlet">
        <c:param name="action" value="GetAdminList"/>
    </c:redirect>
</c:if>
