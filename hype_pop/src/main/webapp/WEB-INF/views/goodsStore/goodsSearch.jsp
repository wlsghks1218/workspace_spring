<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />

	<table class="searchResultGoods">
		<tr>
			<td id="goodsImg">
				<h1>���� �̹���</h1>
			</td>
			<td id="goodsInfo"><span id="goodsName">���� �̸�</span> 
			<span id="goodslLikeCount">���ƿ� ��</span>
				<h3>���� : 20000</h3>
				<h3>�ǰ� ������ ��� �Ͱ� ����� ���� �����</h3>
				<h3>
					���ɻ�: <span>���ɻ� 1</span>, <span>���ɻ� 2</span>, <span>���ɻ� 3</span>
				</h3>
				<h3>
					<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>
				</h3></td>
		</tr>
	</table>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
</html>