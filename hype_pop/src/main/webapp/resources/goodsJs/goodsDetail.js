const rs = replyService;

document.addEventListener("DOMContentLoaded", function () {
    // 수량 조정 기능 추가
    let quantity = 1;
    const quantityInput = document.getElementById('quantity');
    const totalPriceDisplay = document.getElementById('totalPrice');
    
    console.log("가격 : " + goodsPrice);
    
    function updateTotalPrice() {
    	const totalPrice = goodsPrice * quantity;
    	totalPriceDisplay.innerText = totalPrice + "원";
    }
    updateTotalPrice();
    
    document.getElementById('increaseBtn').addEventListener('click', function () {
        quantity++;
        quantityInput.value = quantity;
        updateTotalPrice();
    });

    document.getElementById('decreaseBtn').addEventListener('click', function () {
        if (quantity > 1) {
            quantity--;
            quantityInput.value = quantity;
            updateTotalPrice();
        }
    });
    
    
    let gNo = new URLSearchParams(location.search).get('gNo');
    document.getElementById('addGReply').addEventListener('click', function() {
    	event.preventDefault();
	    const rating = document.getElementById('rating').value;
	    const reviewText = document.getElementById('reviewText').value;
		rs.add({
//			gNo: 10,
			userNo: 1
//			gComment: reviewText
//			gScore: rating
		}, function (result) {
			console.log("result : " + result);
			if (result == "success") {
				alert("댓글이 등록되었습니다.");
			} else {
				alert("댓글 등록 실패");
			}
		});
    });
    

    // 리뷰 작성란의 별점 클릭 기능
    const reviewStars = document.querySelectorAll('#newReviewStars span');
    reviewStars.forEach(star => {
        star.addEventListener('click', function () {
            const rating = this.getAttribute('data-value');
            const ratingInput = document.getElementById('rating');
            const selectedRatingText = document.getElementById('selectedRating');

            reviewStars.forEach(s => {
                s.style.color = 'gray'; // 초기 색상
            });
            for (let i = 1; i <= rating; i++) {
                reviewStars[i - 1].style.color = 'gold'; // 선택된 별
            }

            ratingInput.value = rating;
            selectedRatingText.textContent = '선택한 별점: ' + rating;
        });
    });
});


document.querySelectorAll('.actionButtons button').forEach(a => {
    a.addEventListener('click', (event) => {
        let buttonName = a.id;

        if (buttonName === "addToCart") {
            if (confirm("장바구니로 이동하시겠습니까?")) {
                location.href = "/purchase/goCart";
            }
        } else if (buttonName === "directPurchase") {
            console.log("바로 결제");
            location.href = "/purchase/goPurchase";
        }
    });
});



//별점 선택 기능 추가
const stars = document.querySelectorAll('#newReviewStars span');
stars.forEach(star => {
    star.addEventListener('click', function() {
        const selectedValue = this.getAttribute('data-value');
        document.getElementById('rating').value = selectedValue;
        document.getElementById('selectedRating').innerText = `선택한 별점: ${selectedValue}`;
    });
});


