<c:set var="categoryListDTO" value="${sessionScope.CATEGORY_LIST_DTO}"/>
<c:if test="${empty categoryListDTO}">
    <c:redirect url="MainServlet?action=GetCategoryList" />
</c:if>

