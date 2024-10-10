// 헤더 스팬 클릭 이벤트
document.querySelectorAll('.popUpHeader span').forEach(a => {
  a.addEventListener('click', (event) => {
    event.preventDefault(); 
    
    let headerClick = a.id;
    let searchVal = document.querySelector("#goodsSearchBox").value; // ID 수정
    let sendSearchData = `searchData=${encodeURIComponent(searchVal)}`; // 한국어로 검색할 가능성이 높아서 인코딩 처리를 따로 해 줘야 함

    console.log(sendSearchData);
    
    if (headerClick === "searchBTN") {
      location.href = `/goodsStore/goodsSearch?${sendSearchData}`; // 검색 버튼 클릭 시 이동

    } else if (headerClick === "notice") {
      console.log("알림 아이콘");
    }else if (headerClick === "mainLogo") {
		location.href = "/"
	}
  });
});

document.querySelector("#hamburgerBTN").addEventListener('click', (event) => {
    const menu = document.querySelector("#hamburgerList ul");
    
    // 현재 display 스타일을 확인하여 toggle
    if (menu.style.display === "block") {
        menu.style.display = "none"; // 현재가 block이면 none으로 변경
    } else {
        menu.style.display = "block"; // 현재가 block이 아니면 block으로 변경
    }
});

document.querySelectorAll('.goods-item1').forEach(item => {
    item.addEventListener('click', () => {
        const gNo = item.querySelector('input[type="hidden"]').value; // 숨겨진 input 요소 선택
        location.href = `/goodsStore/goodsDetails?gNo=${gNo}`;
    });
});

// 현재 인기 있는 굿즈 설정하는 곳

let currentIndex1 = 0;
const itemsToShow1 = 4; // 보여줄 아이템 수
const totalItems1 = 8; // 총 아이템 수

document.getElementById('nextBtn1').addEventListener('click', () => {
    if (currentIndex1 < totalItems1 - itemsToShow1) {
        currentIndex1 += 4;
        updateDisplay1();
    }
});

document.getElementById('prevBtn1').addEventListener('click', () => {
    if (currentIndex1 > 0) {
        currentIndex1 -= 4;
        updateDisplay1();
    }
});

function updateDisplay1() {
	const items = document.querySelectorAll('.goods-item1');
	items.forEach((item, index) => {
		item.style.display = (index >= currentIndex1 && index < currentIndex1 + itemsToShow1) ? 'block' : 'none';
	});
}

// 초기 표시
updateDisplay1();



// 관심사 1 굿즈 설정하는 곳

let currentIndex2 = 0;
const itemsToShow2 = 4; // 보여줄 아이템 수
const totalItems2 = 8; // 총 아이템 수

document.getElementById('nextBtn2').addEventListener('click', () => {
    if (currentIndex2 < totalItems2 - itemsToShow2) {
        currentIndex2 += 4;
        updateDisplay2();
    }
});

document.getElementById('prevBtn2').addEventListener('click', () => {
    if (currentIndex2 > 0) {
        currentIndex2 -= 4;
        updateDisplay2();
    }
});

function updateDisplay2() {
    const items = document.querySelectorAll('.goods-item2');
    items.forEach((item, index) => {
        item.style.display = (index >= currentIndex2 && index < currentIndex2 + itemsToShow2) ? 'block' : 'none';
    });
}

// 초기 표시
updateDisplay2();



//관심사 2 굿즈 설정하는 곳

let currentIndex3 = 0;
const itemsToShow3 = 4; // 보여줄 아이템 수
const totalItems3 = 8; // 총 아이템 수

document.getElementById('nextBtn3').addEventListener('click', () => {
    if (currentIndex3 < totalItems3 - itemsToShow3) {
        currentIndex3 += 4;
        updateDisplay3();
    }
});

document.getElementById('prevBtn3').addEventListener('click', () => {
    if (currentIndex3 > 0) {
        currentIndex3 -= 4;
        updateDisplay3();
    }
});

function updateDisplay3() {
    const items = document.querySelectorAll('.goods-item3');
    items.forEach((item, index) => {
        item.style.display = (index >= currentIndex3 && index < currentIndex3 + itemsToShow3) ? 'block' : 'none';
    });
}

// 초기 표시
updateDisplay3();

//관심사 3 굿즈 설정하는 곳

let currentIndex4 = 0;
const itemsToShow4 = 4; // 보여줄 아이템 수
const totalItems4 = 8; // 총 아이템 수

document.getElementById('nextBtn4').addEventListener('click', () => {
    if (currentIndex4 < totalItems4 - itemsToShow4) {
        currentIndex4 += 4;
        updateDisplay4();
    }
});

document.getElementById('prevBtn4').addEventListener('click', () => {
    if (currentIndex4 > 0) {
        currentIndex4 -= 4;
        updateDisplay4();
    }
});

function updateDisplay4() {
    const items = document.querySelectorAll('.goods-item4');
    items.forEach((item, index) => {
        item.style.display = (index >= currentIndex4 && index < currentIndex4 + itemsToShow4) ? 'block' : 'none';
    });
}

// 초기 표시
updateDisplay4();


const goodsNames = document.querySelectorAll('.goods-name');
const maxLength = 40;
let displayName;

goodsNames.forEach((element) => {
    let displayName = element.textContent; // 요소의 텍스트 가져오기

    if (displayName.length > maxLength) {
        displayName = displayName.substring(0, maxLength) + "..."; // 잘라내고 ... 추가
    }

    element.textContent = displayName; // 수정된 텍스트로 업데이트
});