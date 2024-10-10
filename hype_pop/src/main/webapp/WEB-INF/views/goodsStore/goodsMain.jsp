<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h1 {
	text-align: center;
}

h2 {
	padding-left: 25%;
}

#hotGoods {
	display: flex;
	align-items: center;
	margin: auto;
	width: 50%;
	height: 200px;
}

.goods-container1 {
	display: flex;
	overflow: hidden;
	width: 100%; /* 보여줄 영역의 너비 (4개 아이템) */
	height: 100%;
}

.goods-item1 {
	background-color: lightgray;
	min-width: 22.5%; /* 각 아이템의 너비 */
	height: 100%;
	margin: 0 10px; /* 좌우 마진 */
	transition: transform 0.3s ease;
}

#prevBtn1, #nextBtn1 {
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	padding: 10px;
	cursor: pointer;
}

#interestGoods1 {
	display: flex;
	align-items: center;
	margin: auto;
	width: 50%;
	height: 300px;
}

.goods-container2 {
	display: flex;
	overflow: hidden;
	width: 100%; /* 보여줄 영역의 너비 (4개 아이템) */
}

.goods-item2 {
	background-color: lightgray;
	min-width: 22.5%; /* 각 아이템의 너비 */
	height: 190px;
	margin: 0 10px; /* 좌우 마진 */
	transition: transform 0.3s ease;
}

#prevBtn2, #nextBtn2 {
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	padding: 10px;
	cursor: pointer;
}

#interestGoods2 {
	display: flex;
	align-items: center;
	margin: auto;
	width: 50%;
	height: 300px;
}

.goods-container3 {
	display: flex;
	overflow: hidden;
	width: 100%; /* 보여줄 영역의 너비 (4개 아이템) */
}

.goods-item3 {
	background-color: lightgray;
	min-width: 22.5%; /* 각 아이템의 너비 */
	height: 190px;
	margin: 0 10px; /* 좌우 마진 */
	transition: transform 0.3s ease;
}

#prevBtn3, #nextBtn3 {
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	padding: 10px;
	cursor: pointer;
}

#interestGoods3 {
	display: flex;
	align-items: center;
	margin: auto;
	width: 50%;
	height: 300px;
}

.goods-container4 {
	display: flex;
	overflow: hidden;
	width: 100%; /* 보여줄 영역의 너비 (4개 아이템) */
}

.goods-item4 {
	background-color: lightgray;
	min-width: 22.5%; /* 각 아이템의 너비 */
	height: 190px;
	margin: 0 10px; /* 좌우 마진 */
	transition: transform 0.3s ease;
}

#prevBtn4, #nextBtn4 {
	background-color: #f0f0f0;
	border: 1px solid #ccc;
	padding: 10px;
	cursor: pointer;
}
.goods-like{
	height: 8%;
	text-align: right;
}

.goods-name {
    background-color: red;
       display: flex;
    align-items: flex-end; /* 세로 정렬: 아래쪽 */
    justify-content: flex-start; /* 가로 정렬: 왼쪽 */
    height: 80%;
}
.goods-price{
text-align: right;
    background-color: yellow;
    height: 8%;
}

</style>
</head>
<body>
    <jsp:include page="layout/popUpHeader.jsp" />
    <h1>현재 인기있는 굿즈</h1>
    <div id="hotGoods">
        <button id="prevBtn1">◀</button>
        <div class="goods-container1" id="goodsContainer1">
			<c:forEach var="vo" items="${likeGoods }">
            <div class="goods-item1">
            	<input type="hidden" value="${vo.GNo }">
            	<div class="goods-like">${vo.likeCount }</div>
            	<div class="goods-name">${vo.GName }</div>
            	<div class="goods-price">${vo.GPrice }</div>
           	</div>
			</c:forEach>
        </div>
        <button id="nextBtn1">▶</button>
    </div>
    <h1>관심사별 인기 목록</h1>
    <h2>????</h2>
    <div id="interestGoods1">
        <button id="prevBtn2">◀</button>
        <div class="goods-container2" id="goodsContainer2">
  			<c:forEach var="vo" items="${interestOneNotLogin }">
         	   <div class="goods-item2">
	            	<div class="goods-like">${vo.likeCount }</div>
	            	<div class="goods-name">${vo.GName }</div>
	            	<div class="goods-price">${vo.GPrice }</div>
            	</div>
			</c:forEach>
        </div>
        <button id="nextBtn2">▶</button>
    </div>
        <h2>????</h2>
    <div id="interestGoods2">
        <button id="prevBtn3">◀</button>
        <div class="goods-container3" id="goodsContainer3">
  			<c:forEach var="vo" items="${interestTwoNotLogin }">
            	<div class="goods-item3">
	            	<div class="goods-like">${vo.likeCount }</div>
	            	<div class="goods-name">${vo.GName }</div>
	            	<div class="goods-price">${vo.GPrice }</div>
            	</div>
   			</c:forEach>
        </div>
        <button id="nextBtn3">▶</button>
    </div>
        <h2>????</h2>
    <div id="interestGoods3">
        <button id="prevBtn4">◀</button>
        <div class="goods-container4" id="goodsContainer4">
  			<c:forEach var="vo" items="${interestThreeNotLogin }">
         	   <div class="goods-item4">
	            	<div class="goods-like">${vo.likeCount }</div>
	            	<div class="goods-name">${vo.GName }</div>
	            	<div class="goods-price">${vo.GPrice }</div>
            	</div>
  			</c:forEach>
        </div>
        <button id="nextBtn4">▶</button>
    </div>
    <jsp:include page="layout/popUpFooter.jsp" />
    <jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script>
</script>
</html>