<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="#">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Custom Login Page</h1>
	<h2>${error }</h2>
	<h2>${logout }</h2>
	
	<form action="/login" method="post">
		<div>
			<input type="text" name="username">
		</div>	
		<div>
			<input type="password" name="password">
		</div>	
		<div>
			<input type="checkbox" name="remember-me"> 로그인 유지하기
		</div>	
		<div>
			<input type="submit" value="전송">
			<a href="/">홈으로</a>
		</div>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	</form>
</body>
</html>