<c:set var="txtCategory" value="${param.txtCategory}"/>
<c:if test="${empty txtCategory}">
    <c:redirect url="accessoryCategories.jsp"/>
</c:if>
<x:set var="accessoryCategoryDocument" select="$accessoryCategoryListDocument//accessoryCategory[category=$txtCategory]" />
<x:if select="not($accessoryCategoryDocument)">
    <c:redirect url="accessoryCategories.jsp"/>
</x:if>