// CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/boardlist.css';

// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;

// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// pageNum amount Storage 설정
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
if(!pageNum || !amount){
	pageNum = 1;
	amount = 5;
}
setStorageData(pageNum, amount);
//콘솔 - 어플리케이션 - local storage에 저장된 값이 출력됨

let sendData;

// 새 게시글 등록 클릭 이벤트
document.querySelector('#registerBtn').addEventListener('click', () => {
	location.href = '/board/register';
})

// 페이징 버튼 클릭 이벤트
document.querySelectorAll('.page-nation li a').forEach(aEle => {
	aEle.addEventListener('click', e => {
		e.preventDefault();
		pageNum = aEle.getAttribute('href');
		setStorageData(pageNum, amount);
		sendData = `pageNum=${pageNum}&amount=${amount}`;
		location.href = `/board/list?${sendData}`;
	})
})

document.querySelectorAll('tbody a').forEach(a =>{
	a.addEventListener('click', e =>{
		e.preventDefault();
		let bno = a.getAttribute('href');
		sendData = `bno=${bno}&pageNum=${pageNum}&amount=${amount}`;
		location.href = `/board/get?${sendData}`;
	})
})


