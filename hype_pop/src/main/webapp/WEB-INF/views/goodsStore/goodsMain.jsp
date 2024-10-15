<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 전체 레이아웃 */
body {
    margin: 0;
    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
    background-color: #141414;
    color: white;
}

/* 네비게이션 바 */
#popUpHeader {
    background-color: #141414;
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

h1, h2 {
    text-align: center;
    color: #e50914;
    margin-bottom: 20px;
}

/* 굿즈 컨테이너 스타일 */
#hotGoods, #interestGoods1, #interestGoods2, #interestGoods3 {
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 40px 0;
    position: relative;
}

.goods-container1, .goods-container2, .goods-container3, .goods-container4 {
    display: flex;
    overflow: hidden;
    width: 80%;
    height: 300px;
    border-radius: 10px;
    position: relative;
}

.goods-item1, .goods-item2, .goods-item3, .goods-item4 {
    background-color: #333;
    color: white;
    min-width: 200px;
    height: 100%;
    margin: 0 10px;
    border-radius: 10px;
    padding: 10px;
    transition: transform 0.3s ease;
}

.goods-item1:hover, .goods-item2:hover, .goods-item3:hover, .goods-item4:hover {
    transform: scale(1.1);
}

/* 가격, 좋아요, 이름 등 */
.goods-like {
    text-align: right;
    color: #fff;
}

.goods-name {
    background-color: #e50914;
    color: white;
    padding: 10px;
    font-weight: bold;
    margin-top: 20px;
    height: 150px;
    overflow: hidden;
    text-overflow: ellipsis;
}

.goods-price {
    text-align: right;
    color: #f5c518;
    font-weight: bold;
    margin-top: 10px;
}

/* 좌우 버튼 */
#prevBtn1, #nextBtn1, #prevBtn2, #nextBtn2, #prevBtn3, #nextBtn3, #prevBtn4, #nextBtn4 {
    background-color: #e50914;
    border: none;
    color: white;
    font-size: 18px;
    padding: 10px;
    cursor: pointer;
    border-radius: 50%;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    z-index: 10;
}

#prevBtn1, #prevBtn2, #prevBtn3, #prevBtn4 {
    left: 10px;
}

#nextBtn1, #nextBtn2, #nextBtn3, #nextBtn4 {
    right: 10px;
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
            	<input type="hidden" value="${vo.gno }">
            	<div class="goods-like">${vo.likeCount }</div>
            	<div class="goods-name">${vo.gname }</div>
            	<div class="goods-price">${vo.gprice }</div>
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
	            	<div class="goods-name">${vo.gname }</div>
	            	<div class="goods-price">${vo.gprice }</div>
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
	            	<div class="goods-name">${vo.gname }</div>
	            	<div class="goods-price">${vo.gprice }</div>
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
	            	<div class="goods-name">${vo.gname }</div>
	            	<div class="goods-price">${vo.gprice }</div>
            	</div>
  			</c:forEach>
        </div>
        <button id="nextBtn4">▶</button>
    </div>
    <jsp:include page="layout/popUpFooter.jsp" />
    <jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/goodsHeader.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsMain.js"></script>
</html>