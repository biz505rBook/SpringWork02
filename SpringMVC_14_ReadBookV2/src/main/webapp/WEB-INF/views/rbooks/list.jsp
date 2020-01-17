<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>□□□ My JSP Page □□□</title>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>
<header>
	<h2>My Read Book</h2>
</header>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<%@ include file="/WEB-INF/views/rbooks/list-body.jsp" %>

</body>
</html>