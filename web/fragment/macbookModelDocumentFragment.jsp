<c:set var="txtModelID" value="${param.txtModelID}"/>
<c:if test="${empty txtModelID}">
    <c:redirect url="macbookModels.jsp"/>
</c:if>
<x:set var="macbookModelDocument" select="$macbookModelListDocument//macbookModel[modelID=$txtModelID]" />
<x:if select="not($macbookModelDocument)">
    <c:redirect url="macbookModels.jsp"/>
</x:if>