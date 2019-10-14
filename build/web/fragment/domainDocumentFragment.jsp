<c:set var="txtDomain" value="${param.txtDomain}"/>
<c:if test="${empty txtDomain}">
    <c:redirect url="domains.jsp"/>
</c:if>
<x:set var="domainDocument" select="$domainListDocument//domain[domain=$txtDomain]" />
<x:if select="not($domainDocument)">
    <c:redirect url="domains.jsp"/>
</x:if>