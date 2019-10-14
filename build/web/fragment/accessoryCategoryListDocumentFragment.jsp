<c:set var="accessoryCategoryListDocument" value="${sessionScope.ACCESSORY_CATEGORY_LIST_DOCUMENT}"/>
<c:if test="${empty accessoryCategoryListDocument}">
    <c:redirect url="MainServlet">
        <c:param name="action" value="GetAccessoryCategoryList"/>
    </c:redirect>
</c:if>
