// CSS 적용
const CSS_FILE_PATH = '/resources/css/get.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);

// 0번 form data를 변수 f로 선언
const f = document.forms[0];

// 모든 댓글 출력을 위한 reply.js에서 만든 변수 호출
const rs = replyService;

let initialTitle = document.querySelector('td input[name=title]').value;
let initialWriter = document.querySelector('td input[name=writer]').value;
let initialContent = document.querySelector('td textarea[name=content]').value;

showList();
//댓글 목록 가져오는 함수
function showList(){
	let bno = f.bno.value;
	let replyUL = document.querySelector(".chat");
	replyUL.innerHTML = ""; // 기존의 댓글 목록을 초기화
	
	let msg = '';
	rs.getList(bno, function(data){
		if(data.length > 0){
		data.forEach(vo =>{
			msg +=  `<li data-rno=${vo.rno} onclick="modifyModalPage(this)">`;
			msg += 	`<div>`;
			msg += 		`<div class="chat-header">`;
			msg += 			`<strong class="primary-font">${vo.replyer}</strong>`;
         msg +=         	`<small class="pull-right">${displayTime(vo.updateDate)}</small>`;
			msg += 		`</div>`;
			msg +=		`<p>${vo.reply}</p>`;
			msg += 	`</div>`;
			msg += `</li>`;
		})
		}else{
			msg += `<li data-rno=''>`;
			msg += 	`<div>`;
			msg += 		`<div class="chat-header">`;
			msg += 			`<strong class="primary-font"></strong>`;
         msg +=         	`<small class="pull-right"></small>`;
			msg += 		`</div>`;
			msg +=		`<p>댓글이 없습니다.</p>`;
			msg += 	`</div>`;
			msg += `</li>`;
		}
		replyUL.innerHTML = msg;
	})
}

// 게시글 버튼 처리
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) =>{
		
		let type = btn.id;
		switch(type){
		
		// ----- 게시글 버튼 관련 스크립트 -----
		// 게시글 수정 버튼
		case "modifyBtn":
			modify();
			break;
			
			// 게시글 수정 -> 취소 버튼
		case "cancelBtn":
			cancel();
			break;
			
			// 게시글 수정 -> 수정 버튼	
		case "updateBtn":
			updateBoard();
			break;
			
			// 게시글 수정 -> 삭제 버튼
		case "deleteBtn":
			deleteBoard();
			break;
			
			
			// 목록으로 이동 버튼	
		case "indexBtn":
			let pageData = getStorageData();
			let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`;
			location.href = `/board/list?${sendData}`;
			break;
		}
	})
})

// -- 게시글 관련 함수
// 게시글 수정 함수
function modify(){
	document.querySelector('td input[name=title]').removeAttribute("readonly");
	document.querySelector('td textarea[name=content]').removeAttribute("readonly");
	document.querySelector('#modifyBtn').id = "updateBtn";
	document.querySelector('#updateBtn').textContent = "수정완료";
    document.querySelector('#cancelBtn').style.display = 'inline';
    document.querySelector('#deleteBtn').style.display = 'inline';
}

// 게시글 수정 -> 취소 함수
function cancel(){
    document.querySelector('td input[name=title]').value = initialTitle;
    document.querySelector('td input[name=writer]').value = initialWriter;
    document.querySelector('td textarea[name=content]').value = initialContent;
    document.querySelector('td input[name=title]').setAttribute("readonly",true);
    document.querySelector('td textarea[name=content]').setAttribute("readonly",true);
	document.querySelector('#updateBtn').id = "modifyBtn";
	document.querySelector('#modifyBtn').textContent = "게시글 수정";
    document.querySelector('#cancelBtn').style.display = 'none';
    document.querySelector('#deleteBtn').style.display = 'none';
}

// 게시글 수정 -> 수정 함수
function updateBoard(){
	if(f.title.value ==""){
		alert("제목 입려하세여");
	}
	if(f.content.value ==""){
		alert("내용 입력하세여");
	}
	f.action = "/board/modify";
	f.submit();
}

// 게시글 수정 -> 삭제 함수
function deleteBoard(){
	if(confirm("삭제하시겠습니까?")){
		f.action='/board/remove';
		f.submit();
	}else{
		return;
	}
}

// ----- 댓글, 모달 관련 스크립트 -----
const modal = document.querySelector('#modal');
const inputReply = document.querySelector('input[name="reply"]');
const inputReplyer = document.querySelector('input[name="replyer"]');
const inputReplyDate = document.querySelector('input[name="replydate"]');
const addReplyBtn = document.querySelector('#addReplyBtn');
const modifyReplyBtn = document.querySelector('#modifyReplyBtn');
const removeReplyBtn = document.querySelector('#removeReplyBtn');
const closeModalBtn = document.querySelector('#closeModalBtn');


//게시글 버튼 처리
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) =>{
		
		let type = btn.id;
		switch(type){
		// ----- 댓글 버튼 관련 스크립트 -----
		case "replyBtn":
			regReplyModalStyle();
			inputReply.value = '';
			inputReplyer.value = '';
			openModal();
			break;
		case "closeModalBtn":
			closeModal();
			break;
		case "addReplyBtn":
			registerReply();
			break;
		case "modifyReplyBtn":
			modifyReply();
			break;
		case "removeReplyBtn":
			removeReply();
			break;
		}
	})
})

function openModal(){
	modal.style.display = "block";
}

function closeModal(){
	modal.style.display = "none";
}

function regReplyModalStyle(){
	addReplyBtn.classList.remove("hide");
	modifyReplyBtn.classList.add("hide");
	removeReplyBtn.classList.add("hide");
	inputReplyDate.closest('div').classList.add("hide");
	inputReplyer.removeAttribute('readonly');
}

function registerReply(){
	if(inputReply.value ==''){
		alert('내용을 입력해주세요.');
		return
	}
	if(inputReplyer.value ==''){
		alert('작성자를 입력해주세요.');
		return
	}
	rs.add(
			{
				reply : inputReply.value,
				replyer : inputReplyer.value,
				bno : f.bno.value
				
			}, function(result){
				console.log("result : " + result);
				if(result = "success"){
					alert("댓글이 등록되었습니다.")
				}else{
					alert("댓글 등록 실패");
				}
				closeModal();
				showList();
			}
	)
}
function modifyReplyModalStyle(){
	modifyReplyBtn.classList.remove("hide");
	removeReplyBtn.classList.remove("hide");
	addReplyBtn.classList.add("hide");
	inputReplyDate.closest('div').classList.remove("hide");
	inputReplyer.setAttribute('readonly', true);
	inputReplyDate.setAttribute('readonly', true);
}

let rno;

function modifyModalPage(li){
	modifyReplyModalStyle();
	
	rno = li.dataset.rno;
	let reply = li.querySelector('p').textContent;
	let replyer = li.querySelector('strong').textContent;
	let replyDate = li.querySelector('small').textContent;
	
	inputReply.value = reply;
	inputReplyer.value = replyer;
	inputReplyDate.value = replyDate;
	console.log(rno);
	openModal();
}

function modifyReply(){
	if(!inputReply.value){
		alert("수정할 댓글을 입력하세요.");
		return;
	}
	console.log(rno);
	let rvo = {rno : rno, reply : inputReply.value};
	rs.update(rvo, function(result){
		if(!result == 1){
			alert("댓글 수정 실패")
		}else{
			alert("댓글을 수정했습니다.");
		}
		showList();
		closeModal();
	})
}

function removeReply(){
	if(confirm('댓글을 삭제하시겠습니까?')){
		rs.remove(rno, function(result){
			if(!result == 1){
				alert("댓글 삭제 실패")
			}else{
				alert("댓글을 삭제했습니다.");
				showList();
				closeModal();
			}
		})
	}
}


//UTC 시간을 KST로 변환하는 함수
function displayTime(unixTimeStamp) {
	// UTC 시간 문자열을 Date 객체로 변환
	const myDate = new Date(unixTimeStamp);

	const y = myDate.getFullYear();
	const m = String(myDate.getMonth() + 1).padStart(2, '0'); // 월을 두 자리로 맞춤
	const d = String(myDate.getDate()).padStart(2, '0'); // 일을 두 자리로 맞춤

	return `${y}-${m}-${d}`;
}
