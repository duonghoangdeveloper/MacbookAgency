<c:set var="adminInfoDTO" value="${sessionScope.ADMIN_INFO_DTO}"/>
<c:if test="${empty adminInfoDTO}">
    <c:redirect url="login.jsp" />
</c:if>
