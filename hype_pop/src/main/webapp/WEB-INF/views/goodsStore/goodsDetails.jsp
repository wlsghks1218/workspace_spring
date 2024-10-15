<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굿즈 스토어 상세 페이지</title>
<style>
/* 전체 페이지 레이아웃 */
body {
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	background-color: #141414;
	color: #fff;
	margin: 0;
	padding: 0;
}

h1, h2, p, span {
	color: #fff;
}

/* 굿즈 상세 레이아웃 */
.goodsDetails {
	width: 80%;
	margin: 30px auto;
	display: flex;
	flex-direction: row;
	border-radius: 10px;
	background-color: #222;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
	overflow: hidden;
}

#goodsBanner, #goodsInfo {
	width: 50%;
	padding: 20px;
	box-sizing: border-box;
}

#goodsBanner {
	background-color: #333;
	text-align: center;
	display: flex;
	justify-content: center;
	align-items: center;
}

#goodsBanner img {
	max-width: 100%;
	height: auto;
	border-radius: 10px;
}

#goodsInfo {
	background-color: #181818;
	padding: 20px;
}

#goodsInfo span {
	display: block;
	margin-bottom: 15px;
	font-size: 18px;
}

/* 수량 조정 버튼 */
.quantityBar {
	display: flex;
	align-items: center;
	margin: 20px 0;
}

.quantityBar button {
	width: 40px;
	height: 40px;
	background-color: #444;
	color: #fff;
	border: none;
	font-size: 20px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.quantityBar button:hover {
	background-color: #888;
}

#quantity {
	width: 60px;
	text-align: center;
	border: 1px solid #333;
	font-size: 18px;
	background-color: #222;
	color: #fff;
	margin: 0 10px;
}

.totalPrice {
	font-size: 22px;
	font-weight: bold;
	color: #e50914;
}

/* 버튼 스타일 */
.actionButtons {
	display: flex;
	gap: 15px;
}

.actionButtons button {
	padding: 15px 20px;
	background-color: #e50914;
	color: white;
	border: none;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.actionButtons button:hover {
	background-color: #c3070a;
}

.actionButtons button.directPurchase {
	background-color: #f44336;
}

/* 굿즈 상세 이미지 */
.goodsDetailImg {
	width: 80%;
	margin: 30px auto;
	text-align: center;
	background-color: #222;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

/* 별점 스타일 */
.StarRating span {
	font-size: 30px;
	cursor: pointer;
}

.StarRating span:hover, .StarRating span.active {
	color: gold;
}

#userReviews {
    width: 80%;
    margin: 0 auto 30px auto; /* 중앙 정렬 */
    padding: 20px;
    background-color: #222;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

/* 후기 제목 스타일 */
#userReviews h2 {
    color: #fff;
    font-size: 28px; /* 크기를 살짝 키움 */
    font-weight: 600; /* 글자 두께를 조금 더 두껍게 */
    margin-bottom: 20px; /* 제목과 댓글 목록 사이 간격 */
    text-align: left; /* 제목을 왼쪽 정렬 */
    padding-bottom: 10px;
    border-bottom: 2px solid #e50914; /* 아래에 구분선 추가 */
}

/* 댓글 목록 */
.myChat {
    list-style-type: none;
    padding: 0;
    color: #fff;
}

.myChat li {
    background-color: #333;
    padding: 15px;
    margin-bottom: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

/* 유저 리뷰 스타일 */
#userReviews ul {
    list-style-type: none;
    padding: 0;
}

#userReviews ul li {
    background-color: #333;
    padding: 15px;
    margin-bottom: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    color: #fff;
    width: 80%; /* 댓글 영역의 너비를 80%로 설정 */
    margin: 0 auto 10px; /* 가운데 정렬 */
    position: relative; /* 케밥 메뉴 위치를 위한 설정 */
}


.reviewItem {
	margin-bottom: 20px;
}

.review-content {
    width: 80%;
    display: inline-block; /* 댓글 내용을 차지하는 영역 */
}


/* 평균 별점 영역 */
.avgStarRating {
	display: flex;
	align-items: center;
	margin: 20px auto;
	width: 50%;
	justify-content: space-between;
}

/* 케밥 메뉴 */
.kebab-menu {
    position: absolute;
    top: 15px;
    right: 10px; /* 우측에서 10px 떨어지게 */
    cursor: pointer;
}

.menu-options {
    display: none;
    position: absolute;
    right: 0;
    top: 30px;
    background: white;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
    padding: 10px;
    border-radius: 8px;
    z-index: 1000;
}

.menu-options button {
    display: block;
    width: 100%;
    padding: 10px;
    margin-bottom: 5px;
    cursor: pointer;
    background-color: #e50914;
    color: white;
    border: none;
    border-radius: 5px;
    text-align: center;
}

.menu-options button:hover {
    background-color: #c3070a;
}

.menu-options .delete-btn {
    background-color: #f44336;
}
#reviewForm {
    display: flex;
    flex-direction: column;
    align-items: center; /* 가로 축 중앙 정렬 */
    justify-content: center; /* 세로 축 중앙 정렬 */
    width: 80%; /* 폼 너비 설정 */
    margin: 30px auto; /* 가운데 정렬을 위해 자동 마진 */
    background-color: #222;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

#reviewText {
    width: 100%;
    background-color: #333;
    color: #fff;
    border: 1px solid #444;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 15px;
    font-size: 16px;
}

#addGReply {
    background-color: #e50914;
    color: #fff;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s;
}

#addGReply:hover {
    background-color: #c3070a;
}

.StarRating {
    display: flex;
    justify-content: center; /* 별점도 중앙 정렬 */
    margin-bottom: 10px;
}

.StarRating span {
    font-size: 30px;
    cursor: pointer;
    color: gold;
}

#selectedRating {
    margin-bottom: 15px;
    text-align: center;
}


</style>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />
	<div class="goodsDetails">
		<div id="goodsBanner">굿즈 배너 이미지</div>
		<div id="goodsInfo">
			<span id="goodsLike">좋아요: ${goods.likeCount }회</span> <span
				id="goodsName">상품명: ${goods.gname }</span> <span id="goodsPrice">가격:
				${goods.gprice }</span> <span id="goodsDes"> ${goods.gexp } </span> <span
				id="endDate">판매 종료일: ${goods.sellDate }</span>
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
				<button id="directPurchase" class="directPurchase">바로 결제</button>
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
		<textarea id="reviewText" name="reviewText"
			placeholder="후기를 작성해주세요..." rows="5" cols="50"></textarea>
		<input type="hidden" id="rating" name="rating" value="0"> <input
			type="button" id="addGReply" value="등록하기">
	</form>
	<div class="avgStarRating" id="avgReviewStars">
		<span>평균 별점</span><span class="avgScore"></span> <span data-value="1">★</span>
		<span data-value="2">★</span> <span data-value="3">★</span> <span
			data-value="4">★</span> <span data-value="5">★</span>
	</div>
	<div id="userReviews">
		<h2>후기들</h2>
		<ul class="myChat">
			<!-- 댓글 목록이 여기에 추가됩니다 -->
		</ul>
	</div>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/gReply.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsDetail.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsHeader.js"></script>
<script>
    var goodsPrice = ${goods.gprice};
    console.log(goodsPrice);
</script>
</html>