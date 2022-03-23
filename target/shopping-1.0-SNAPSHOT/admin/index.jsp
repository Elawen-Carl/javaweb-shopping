<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${ empty sessionScope.admin }">
    <jsp:forward page="pages/login.jsp"></jsp:forward>
</c:if>
<c:if test="${ sessionScope.admin }">
    <jsp:forward page="pages/index.jsp"></jsp:forward>
    
</c:if>
