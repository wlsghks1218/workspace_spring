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
        const vo = {
        	gNo : gNo,
        	userNo : 1,
            gScore: rating,
            gComment: reviewText
        };
        console.log(vo);
        fetch('/gReply/new', {
            method: 'POST',
            body: JSON.stringify(vo),
            headers: {'Content-type':'application/json; charset=utf-8'}
        })
        .then(response => response.text())
        .then(data => {
            alert(data); // 서버에서 반환한 메시지를 알림으로 표시
            // 성공 시 추가 작업 (예: 리뷰 목록 업데이트)
        })
        .catch(error => {
            console.error('에러:', error);
            alert('리뷰 등록 중 문제가 발생했습니다.');
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


