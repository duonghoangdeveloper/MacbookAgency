<c:set var="macbookModelListDocument" value="${sessionScope.MACBOOK_MODEL_LIST_DOCUMENT}"/>
<c:if test="${empty macbookModelListDocument}">
    <c:redirect url="MainServlet">
        <c:param name="action" value="GetMacbookModelList"/>
    </c:redirect>
</c:if>
