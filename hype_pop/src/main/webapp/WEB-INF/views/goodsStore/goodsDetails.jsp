<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굿즈 스토어 상세 페이지</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 20px;
}

.goodsDetails {
	width: 50%;
	max-width: 1200px; /* 최대 너비 설정 */
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
	margin: 30px auto;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	background-color: #fff;
	box-sizing: border-box;
}

#goodsBanner, #goodsInfo {
	width: 50%; /* 모든 영역의 너비를 50%로 설정 */
	height: 480px; /* 세로 크기를 동일하게 설정 */
	box-sizing: border-box;
	overflow: hidden; /* 내용이 넘칠 경우 숨기기 */
}

#goodsBanner {
	background-color: #f0f0f0;
	text-align: center;
	padding: 20px;
	border-radius: 8px 0 0 8px;
}

#goodsInfo {
	padding: 20px;
	background-color: lightgray;
	border-radius: 0 8px 8px 0;
}

#goodsInfo span {
	display: block;
	margin-bottom: 10px;
}

.quantityBar {
	display: flex;
	align-items: center;
	margin: 10px 0;
}

.quantityBar button {
	width: 30px;
	height: 30px;
	background-color: #ddd;
	border: none;
	font-size: 18px;
	cursor: pointer;
}

#quantity {
	width: 60px;
	text-align: center;
	border: 1px solid #ccc;
	margin: 0 10px;
}

.price, .totalPrice {
	font-size: 18px;
	margin-bottom: 10px;
}

.actionButtons {
	display: flex;
	gap: 10px; /* 버튼 사이 간격 조정 */
}

.actionButtons button {
	padding: 10px 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
	font-size: 16px;
}

.actionButtons button.directPurchase {
	background-color: #f44336;
}

.goodsDetailImg {
	width: 50%; /* 이미지 영역도 50%로 설정 */
	aspect-ratio: 1.5; /* 예: 3:2 비율 유지 */
	background-color: lightgray;
	margin: 30px auto;
	text-align: center;
	padding: 20px;
	border-radius: 8px;
	box-sizing: border-box;
}

.StarRating span {
	font-size: 30px;
	cursor: pointer;
}

.StarRating span:hover, .StarRating span.active {
	color: gold;
}

#userReviews {
	margin:auto;
	margin-top: 30px;
	width: 50%; /* 후기 영역도 50%로 설정 */
}

#reviewForm {
	margin: auto;
	margin-top: 20px;
	width: 50%; /* 리뷰 작성 폼도 50%로 설정 */
}

#reviewText {
	margin: auto;
	width: 90%;
}

#goodsLike {
	text-align: right;
}
.avgStarRating{
	margin: auto;
	width: 50%;
}
</style>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />
	<div class="goodsDetails">
		<div id="goodsBanner">굿즈 배너 이미지</div>
		<div id="goodsInfo">
			<span id="goodsLike">좋아요: ${goods.likeCount }회</span>
			<span id="goodsName">상품명: ${goods.GName }</span>
			<span id="goodsPrice">가격: ${goods.GPrice }</span>
			<span id="goodsDes"> ${goods.GExp } </span>
			<span id="endDate">판매 종료일: ${goods.sellDate }</span>
			<!-- 수량 조정 바 -->
			<div class="quantityBar">
				<button id="decreaseBtn">-</button>
				<input type="text" id="quantity" value="1" readonly />
				<button id="increaseBtn">+</button>
			</div>
			<!-- 총 가격 -->
			<div class="totalPrice">
				총 가격: <span id="totalPrice"></span>
			</div>
			<!-- 장바구니, 바로 결제 버튼 -->
			<div class="actionButtons">
				<button id="addToCart">장바구니 담기</button>
				<button id="directPurchase">바로 결제</button>
			</div>
		</div>
	</div>
	<div class="goodsDetailImg">
		<h1>굿즈 상세 이미지</h1>
		<!-- 여기에 굿즈 상세 이미지가 들어갑니다 -->
	</div>

	<!-- 리뷰 작성 폼 -->
	<form id="reviewForm" method="post">
		<div class="StarRating" id="newReviewStars">
			<span data-value="1">★</span>
			<span data-value="2">★</span>
			<span data-value="3">★</span>
			<span data-value="4">★</span>
			<span data-value="5">★</span>
		</div>
		<p id="selectedRating">선택한 별점: 0</p>
		<textarea id="reviewText" name="reviewText"	placeholder="후기를 작성해주세요..." rows="5" cols="50"></textarea>
		<input type="hidden" id="rating" name="rating" value="0">
		<input type="button" id="addGReply" name="addGReply" value="등록하기">
	</form>
	<div class="avgStarRating" id="avgReviewStars">
		평균 별점
		<span data-value="1">★</span>
		<span data-value="2">★</span>
		<span data-value="3">★</span>
		<span data-value="4">★</span>
		<span data-value="5">★</span>
	</div>
	<div id="userReviews">
		<h2>내가 남긴 후기</h2>
		<div id="reviewList">
			<!-- 리뷰 목록이 동적으로 여기에 추가됩니다. -->
		</div>
	</div>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/goodsDetail.js">
</script>
<script>
	var goodsPrice = ${goods.GPrice};
	console.log(goodsPrice);
</script>
</html>
