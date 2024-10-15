const rs = replyService;

document.addEventListener("DOMContentLoaded", function () {
    const decreaseBtn = document.getElementById('decreaseBtn');
    const increaseBtn = document.getElementById('increaseBtn');
    const quantityInput = document.getElementById('quantity');
    const totalPriceDisplay = document.getElementById('totalPrice');
    const goodsPrice = parseFloat(document.getElementById('goodsPrice').textContent.replace(/[^0-9]/g, '')); // 굿즈 가격

    // 수량 조절
    decreaseBtn.addEventListener('click', () => {
        let quantity = parseInt(quantityInput.value);
        if (quantity > 1) {
            quantity--;
            quantityInput.value = quantity;
            totalPriceDisplay.textContent = (quantity * goodsPrice).toLocaleString() + ' 원';
        }
    });

    increaseBtn.addEventListener('click', () => {
        let quantity = parseInt(quantityInput.value);
        quantity++;
        quantityInput.value = quantity;
        totalPriceDisplay.textContent = (quantity * goodsPrice).toLocaleString() + ' 원';
    });

    // 처음에 총 가격 설정
    totalPriceDisplay.textContent = (1 * goodsPrice).toLocaleString() + ' 원';

    // 별점 선택
    const starRating = document.querySelectorAll('#newReviewStars span');
    const ratingInput = document.getElementById('rating');
    const selectedRating = document.getElementById('selectedRating');

    starRating.forEach(star => {
        star.addEventListener('click', function () {
            const rating = this.getAttribute('data-value');
            ratingInput.value = rating;
            selectedRating.textContent = '선택한 별점: ' + rating;
            starRating.forEach(s => s.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // 댓글 추가 기능
    document.getElementById('addGReply').addEventListener('click', function (event) {
        event.preventDefault();
        const rating = document.getElementById('rating').value;
        const reviewText = document.getElementById('reviewText').value;
        const gno = new URLSearchParams(location.search).get('gno');
        const userId = "유저ID입니다.";

        rs.add({
            gno: gno,
            userNo: 2,
            gcomment: reviewText,
            gscore: rating,
            userId: userId
        }, function (result) {
            if (result === "success") {
                alert("댓글이 등록되었습니다.");
            } else {
                alert("댓글 등록 실패");
            }
        });
    });

    // 댓글 목록 표시
    function showMyList() {
        const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 1;
        let replyUL = document.querySelector(".myChat");
        replyUL.innerHTML = ""; // 기존 댓글 초기화

        rs.getList(gno, userNo, function (data) {
            let msg = '';
            const myReply = data.service2Result;

            if (myReply) {
                msg += `<li data-rno=${myReply.greplyNo} class="my-comment">`;
                msg += `<div class="chat-header">`;
                msg += `<strong class="primary-font">내 댓글: ${myReply.userId}</strong>`;
                msg += `<small class="pull-right">${displayTime(myReply.gupdateDate)}</small>`;
                msg += `<div class="kebab-menu">⋮</div>`;
                msg += `<div class="menu-options">`;
                msg += `<button class="edit-btn">수정하기</button>`;
                msg += `<button class="delete-btn">삭제하기</button>`;
                msg += `</div></div>`;
                msg += `<p>${myReply.gcomment}</p>`;
                msg += `</li>`;
            }

            const allReplies = data.service1Result;
            allReplies.forEach(vo => {
                msg += `<li data-rno=${vo.greplyNo}>`;
                msg += `<div class="chat-header">`;
                msg += `<strong class="primary-font">${vo.userId}</strong>`;
                msg += `<small class="pull-right">${displayTime(vo.gupdateDate)}</small>`;
                msg += `<p>${vo.gcomment}</p>`;
                msg += `</li>`;
            });

            replyUL.innerHTML = msg;

            // 케밥 메뉴 기능 추가 (내 댓글에만 표시)
            document.querySelectorAll('.my-comment .kebab-menu').forEach(icon => {
                icon.addEventListener('click', function () {
                    const menu = this.nextElementSibling;
                    menu.style.display = menu.style.display === 'none' ? 'block' : 'none';
                });
            });
        });
    }
    showMyList();

    // UTC 시간을 KST로 변환하는 함수
    function displayTime(unixTimeStamp) {
        const myDate = new Date(unixTimeStamp);
        const y = myDate.getFullYear();
        const m = String(myDate.getMonth() + 1).padStart(2, '0');
        const d = String(myDate.getDate()).padStart(2, '0');
        return `${y}-${m}-${d}`;
    }
});