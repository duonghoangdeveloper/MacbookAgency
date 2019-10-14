<c:set var="domainListDocument" value="${sessionScope.DOMAIN_LIST_DOCUMENT}"/>
<c:if test="${empty domainListDocument}">
    <c:redirect url="MainServlet">
        <c:param name="action" value="GetDomainList"/>
    </c:redirect>
</c:if>
