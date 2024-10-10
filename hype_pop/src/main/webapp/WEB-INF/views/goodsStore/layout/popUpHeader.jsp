<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box; /* Add box-sizing for better layout control */
	}
	.popUpHeader {
		width: 100%;
		display: flex; /* Use flexbox for better layout */
		align-items: center; /* Center items vertically */
		position: relative; /* 절대 위치를 위해 상대 위치 설정 */
	}
	#hamburgerList ul {
		list-style: none;
		padding: 0;
		position: absolute; /* 절대 위치 */
		left: 0; /* 왼쪽 정렬 */
		z-index: 1000; /* z축 상단에 표시 */
		background: white; /* 배경색 */
		box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* 그림자 효과 */
		display: none;
	}
	#hamburgerList ul li {
	    padding: 10px 15px; /* 내부 여백 */
	    cursor: pointer; /* 커서 모양 변경 */
	    transition: background-color 0.3s; /* 배경색 변화에 애니메이션 추가 */
	}
	
	#hamburgerList ul li:hover {
	    background-color: #f0f0f0; /* 마우스 오버 시 배경색 변화 */
	}
	
	#hamburgerList ul li:active {
	    background-color: #e0e0e0; /* 클릭 시 배경색 변화 */
	}
	.show {
		display: block; /* Show items when necessary */
	}
	#hamburgerBTN {
		width: 10px; /* Adjusted size for better usability */
		height: 10px;
		cursor: pointer; /* Change cursor to pointer */
	}
	.popUpHeader div {
		text-align: center;
		padding: 0 1%;
	}
	#hamburgerDiv {
		flex: 0 0 5%; /* Fixed width */
	}
	#mainLogoDiv {
		flex: 0 0 10%; /* Adjusted for better proportion */
	}
	#goodsMainLogoDiv {
		flex: 0 0 10%; /* Adjusted for better proportion */
	}
	#goodsSearchBoxDiv {
		flex: 1; /* Take up remaining space */
	}
	#noticeDiv {
		flex: 0 0 5%; /* Fixed width */
	}
	#goodsSearchBox {
		width: 70%; /* Adjust input width */
	}
</style>
</head>
<body>
   	<div class="popUpHeader"> 
   		<div id="hamburgerDiv"><img id="hamburgerBTN" src="/resources/images/hamburger.png"></div>
   		<div id="mainLogoDiv"><span id="mainLogo">메인 로고</span></div>
   		<div id="goodsMainLogoDiv"><span id="goodsMainLogo">굿즈 메인 로고</span></div>
		<div id="goodsSearchBoxDiv"><input type="text" id="goodsSearchBox"> <span id="searchBTN">검색</span></div>
		<div id="noticeDiv"><span id="notice">알림</span></div>
	</div>
	<div id="hamburgerList">
		<ul>
			<li id="searchPopUp">팝업 스토어 검색</li>
			<li id="goodsSearch">굿즈 검색</li>
			<li id="aroundMe">내 주변</li>
			<li id="calendar">캘린더</li>
			<li id="support">고객센터</li>
			<li id="login">로그인</li>
			<li id="signIn">회원가입</li>
		</ul>
	</div>
<div class="main">