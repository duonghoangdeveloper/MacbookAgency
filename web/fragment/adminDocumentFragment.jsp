<c:set var="txtUsername" value="${param.txtUsername}"/>
<c:if test="${empty txtUsername}">
    <c:redirect url="admins.jsp"/>
</c:if>
<x:set var="adminDocument" select="$adminListDocument//admin[username=$txtUsername]" />
<x:if select="not($adminDocument)">
    <c:redirect url="admins.jsp"/>
</x:if>