const rs = replyService;

document.addEventListener("DOMContentLoaded", function () {
    const decreaseBtn = document.getElementById('decreaseBtn');
    const increaseBtn = document.getElementById('increaseBtn');
    const quantityInput = document.getElementById('quantity');
    const totalPriceDisplay = document.getElementById('totalPrice');
    const goodsPrice = parseFloat(document.getElementById('goodsPrice').textContent.replace(/[^0-9]/g, '')); // 굿즈 가격

    function displayAvgStars(avgScore) {
        const avgStarsContainer = document.getElementById('avgStarsContainer');
        let stars = '';
        for (let i = 1; i <= 5; i++) {
            if (i <= avgScore) {
                stars += '<span style="color: gold;">★</span>'; // 금색 별
            } else {
                stars += '<span style="color: gray;">★</span>'; // 회색 별
            }
        }
        avgStarsContainer.innerHTML = stars;
    }
    const avgStarsString = parseFloat('${avgStarsString}'); // JSP에서 avgStarsString 값 가져오기
    displayAvgStars(avgStarsString); // 평균 별점 표시
    
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

    totalPriceDisplay.textContent = (1 * goodsPrice).toLocaleString() + ' 원';

    const starRating = document.querySelectorAll('#newReviewStars span');
    const ratingInput = document.getElementById('rating');
    const selectedRating = document.getElementById('selectedRating');

    // 기본 별은 회색
    starRating.forEach(star => {
        star.style.color = 'gray';
    });

    // 별점 hover 및 클릭 처리
    starRating.forEach((star, index) => {
        star.addEventListener('mouseover', function () {
            for (let i = 0; i <= index; i++) {
                starRating[i].style.color = 'gold';
            }
        });

        star.addEventListener('mouseout', function () {
            starRating.forEach(s => s.style.color = 'gray');
            const selectedValue = ratingInput.value;
            for (let i = 0; i < selectedValue; i++) {
                starRating[i].style.color = 'gold';
            }
        });

        star.addEventListener('click', function () {
            const rating = this.getAttribute('data-value');
            ratingInput.value = rating;
            selectedRating.textContent = '선택한 별점: ' + rating;

            starRating.forEach(s => s.style.color = 'gray');
            for (let i = 0; i < rating; i++) {
                starRating[i].style.color = 'gold';
            }
        });
    });

    // gscore에 따라 별점 표시
    function displayStars(score, container) {
        let stars = '';
        for (let i = 1; i <= 5; i++) {
            if (i <= score) {
                stars += '<span style="color: gold;">★</span>'; // 금색 별
            } else {
                stars += '<span style="color: gray;">★</span>'; // 회색 별
            }
        }
        container.innerHTML = stars;
    }

    // 댓글 등록 기능
    document.getElementById('addGReply').addEventListener('click', function (event) {
        event.preventDefault();
        const rating = document.getElementById('rating').value;
        const reviewText = document.getElementById('reviewText').value;
        const gno = new URLSearchParams(location.search).get('gno');
        const userId = "유저ID입니다."; // 유저 ID 값 설정

        rs.add({
            gno: gno,
            userNo: 2,
            gcomment: reviewText,
            gscore: rating,
            userId: userId
        }, function (result) {
            if (result === "success") {
                alert("댓글이 등록되었습니다.");
                showMyList(); // 댓글 목록 새로고침
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
        replyUL.innerHTML = "";

        rs.getList(gno, userNo, function (data) {
            let msg = '';
            const myReply = data.service2Result;

            if (myReply) {
                msg += `<li data-rno=${myReply.greplyNo} class="my-comment">`;
                msg += `<div class="chat-header">`;
                msg += `<div class="userRating"></div>`;
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
                msg += `<div class="userRating"></div>`;
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
            
            // 내 댓글과 모든 댓글의 별점 표시
            if (myReply) {
                const myUserRatingContainer = replyUL.querySelector('.my-comment .userRating');
                displayStars(myReply.gscore, myUserRatingContainer);  // gscore 값을 기반으로 별점 표시
            }

            allReplies.forEach(vo => {
                const userRatingContainer = replyUL.querySelector(`li[data-rno="${vo.greplyNo}"] .userRating`);
                displayStars(vo.gscore, userRatingContainer);  // gscore 값을 기반으로 별점 표시
            });
        });
    }
    showMyList();

    function displayTime(unixTimeStamp) {
        const myDate = new Date(unixTimeStamp);
        const y = myDate.getFullYear();
        const m = String(myDate.getMonth() + 1).padStart(2, '0');
        const d = String(myDate.getDate()).padStart(2, '0');
        return `${y}-${m}-${d}`;
    }
    
    
    function likeBtnChange() {
        const likeBtn = document.querySelector('#chkLike'); // 좋아요 버튼
        const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 1;

        fetch('/goodsStore/chkLike/' + gno + '/' + userNo)
            .then(response => response.json())
            .then(result => {
                if (result == 0) {
                	likeIcon.src = "/resources/images/emptyHeart.png"
                } else if (result == 1) {
                	likeIcon.src = "/resources/images/fullHeart.png"
                }
            })
            .catch(err => console.error('Error:', err));
    }
    likeBtnChange();
    
    
    document.querySelector("#chkLike").addEventListener('click', () => {
    	const likeBtn = document.querySelector('#chkLike');
        const gno = new URLSearchParams(location.search).get('gno');
        const userNo = 1;

        fetch('/goodsStore/changeLike/' + gno + '/' + userNo)
            .then(response => response.json())
            .then(result => {
            	likeBtnChange();
                return fetch('/goodsStore/getLikeCount/' + gno);
            })
            .then(response => response.json())
            .then(likeCount => {
                document.querySelector("#goodsLike").textContent = "좋아요: " + likeCount + "회";
            })
            .catch(err => console.error('Error:', err));
    });
});