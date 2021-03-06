<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<script>
$(function() {
	var toolbar=[
		['style',['bold','italic','underline']],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','hr','picture']],
		['view',['fullscreen','codeview']]
	]

	$("#content").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		height:'200px',
		toolbar:toolbar,
		disableDragAndDrop:true
	})
})

</script>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<fieldset class="email-write-box">
<form:form modelAttribute="emailVO">
	<div class="in-box">
		<label for="fromEmail">보내는 Email</label>
		<form:input path="fromEmail"/>
	</div>
	
	<div class="in-box">
		<label for="to_email">받는 Email</label>
		<form:input path="to_email"/>
	</div>
	
	<div class="in-box">
		<label for="send_date">작성일자</label>
		<form:input path="sendDate"/>
	</div>
	
	<div class="in-box">
		<label for="send_time">작성시각</label>
		<form:input path="sendTime"/>
	</div>
	
	<div class="in-box">
		<label for="fromName">작성자</label>
		<form:input path="fromName"/>
	</div>
	
	<div class="in-box">
		<label for="to_name">받는사람</label>
		<form:input path="to_name"/>
	</div>
	
	<div class="in-box">
		<label for="subject">제목</label>
		<form:input path="subject"/>
	</div>
	
	<div class="in-box">
		<label for="content">내용</label>
		<form:textarea path="content"/>
	</div>
	
	<div class="in-box">
		<button>메일 보내기</button>
	</div>
</form:form>
</fieldset>