<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/*
EL(extend language) tag:= jsp에서 디자이너ㅏ와 개발자의 협업중에
많은 문제들이 발생을 한다.
jsp(html) 디자이너에게 java코드 학습을 강요하고 그러는 과정에서
문제들을 일으키기 때문이다.

java진영에서는 이러한 문제를 줄이기 위해 html코드내에서 최소한의 java 문법만을
사용하여 디자이너와 협업을 할수 있도록 EL tag라는 특별한 언어 구조를 만들어 냈다.

현재 MVC패턴에서도 대부분의 코드는 EL tag로 구현이 되고 있다.

${변수, 연산식, 등등...}
*/
%>
<h3>${param.strName }의 페이지</h3>
<h3>${30+40 }</h3>
<h3>${100*100 }</h3>
<h3>${'test text'}</h3>
<h3>${param.num1+param.num2 }</h3>
</body>
</html>