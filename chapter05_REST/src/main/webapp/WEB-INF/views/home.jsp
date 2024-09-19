<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<button id="btn">전송</button>
</body>
<script type="text/javascript">
	document.querySelector("#btn").addEventListener('click', () => {
		
		fetch('test/ticket.json', 
				{
					method : 'post',
 					body : JSON.stringify({
						tno : 1,
						owner : 'kim',
						grade : 'gold'
					}),
					headers : {'Content-type' : 'application/json; charset=utf-8'}
				})
			.then(response => response.json())
			.then(json => {
				console.log(json);	
			})
			.catch(err => console.log(err));
	});
</script>
</html>
