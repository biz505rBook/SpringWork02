<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>MY EMS</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/resources/css/main.css?ver=2020-01-22">
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/resources/css/table-list.css?ver=2020-01-22">
<link rel="stylesheet" type="text/css" 
		href="${rootPath}/resources/css/email-write.css?ver=2020-01-22">

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
<c:choose>
	<c:when test="${BODY=='WRITE'}">
		<%@ include file="/WEB-INF/views/body/ems/write.jsp" %>
	</c:when>
	<c:when test="${BODY=='VIEW'}">
		<%@ include file="/WEB-INF/views/body/ems/view.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/WEB-INF/views/body/ems/list.jsp" %>
	</c:otherwise>
</c:choose>

<style>
	div.login-modal{
		position:fixed;
		top:0;
		left:0;
		width: 100%;
		height: 100%;
		z-index:2;
		background-color: rgba(0,0,0,0.4);
		display: none;
	}
	div.login-modal form{
		position: relative;
		top:200px;
		margin: 10px auto;
		
	}
</style>
<div class="login-modal">
	<%@ include file="/WEB-INF/views/login.jsp" %>
</div>
</body>
</html>