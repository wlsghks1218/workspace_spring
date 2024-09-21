// CSS 적용
const CSS_FILE_PATH = '/resources/css/get.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);

// 확장자 정규식
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
// 파일크기 제한 - 5MB
const MAX_SIZE = 5242880;

// 0번 form data를 변수 f로 선언
const f = document.forms[0];

// 모든 댓글 출력을 위한 reply.js에서 만든 변수 호출
const rs = replyService;

// 게시글 관련 변수 초기화
let initialTitle = document.querySelector('td input[name=title]').value;
let initialWriter = document.querySelector('td input[name=writer]').value;
let initialContent = document.querySelector('td textarea[name=content]').value;

// 댓글 및 첨부파일 목록 표시
showList();
showAttachList();

// ----- 게시글 관련 함수 -----

// 게시글 버튼 처리
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) => {
		let type = btn.id;
		switch (type) {
			case "modifyBtn":
				modify();
				break;
			case "cancelBtn":
				cancel();
				break;
			case "updateBtn":
				e.preventDefault();
				updateBoard();
				break;
			case "deleteBtn":
				deleteBoard();
				break;
			case "indexBtn":
				let pageData = getStorageData();
				let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`;
				location.href = `/board/list?${sendData}`;
				break;
		}
	});
});

// 게시글 수정 함수
function modify() {
	document.querySelector('td input[name=title]').removeAttribute("readonly");
	document.querySelector('td textarea[name=content]').removeAttribute("readonly");
	document.querySelector('#modifyBtn').id = "updateBtn";
	document.querySelector('#updateBtn').textContent = "수정완료";
	document.querySelector('#cancelBtn').style.display = 'inline';
	document.querySelector('#deleteBtn').style.display = 'inline';
	document.querySelector('input[name=uploadFile]').style.display = 'inline';
	document.querySelectorAll('.uploadResult ul li span').forEach(span => {
		span.style.display = 'inline';
	});
}

// 게시글 수정 -> 취소 함수
function cancel() {
	document.querySelector('td input[name=title]').value = initialTitle;
	document.querySelector('td input[name=writer]').value = initialWriter;
	document.querySelector('td textarea[name=content]').value = initialContent;
	document.querySelector('td input[name=title]').setAttribute("readonly", true);
	document.querySelector('td textarea[name=content]').setAttribute("readonly", true);
	document.querySelector('#updateBtn').id = "modifyBtn";
	document.querySelector('#modifyBtn').textContent = "게시글 수정";
	document.querySelector('#cancelBtn').style.display = 'none';
	document.querySelector('#deleteBtn').style.display = 'none';
	document.querySelector('input[name=uploadFile]').style.display = 'none';
	document.querySelectorAll('.uploadResult ul li span').forEach(span => {
		span.style.display = 'none';
	});
	showAttachList();
}

// 게시글 수정 -> 수정 함수
function updateBoard() {
	let str = '';
	if (f.title.value == "") {
		alert("제목 입력하세여");
	}
	if (f.content.value == "") {
		alert("내용 입력하세여");
	}

	// delete 부분
	for (let i = 0; i < deleteAttach.length; i++) {
		fetch('/deleteFile', {
				method: 'post',
				body: JSON.stringify(deleteAttach[i]),
				headers: {
					'Content-type': 'application/json'
				}
			})
			.then(response => response.text())
			.then(result => {
				console.log(result);
				if (result == "deleted") {
					alert("첨부파일 삭제했습니다.");
				}
			})
			.catch(err => console.log(err));
	}

	// 파일 추가
	uploadResultArr.forEach(file => {
		formData.append("uploadFile", file); // 'uploadFile' 이름으로 파일 추가
	});

	// fetch로 파일 업로드
	fetch('/uploadAsyncAction', {
			method: 'POST',
			body: formData
		})
		.then(response => response.json())
		.then(json => {
			console.log(json);
			// 업로드 결과 처리 및 hidden input 생성
			let str1 = '';
			json.forEach((file, index) => {
				if (file.uploadPath && file.uuid && file.fileName) { // 값이 비어있지 않은 경우에만 추가
					str1 += `<input type="hidden" name="attachList[${index}].uploadPath" value="${file.uploadPath}"/>`;
					str1 += `<input type="hidden" name="attachList[${index}].uuid" value="${file.uuid}"/>`;
					str1 += `<input type="hidden" name="attachList[${index}].fileName" value="${file.fileName}"/>`;
				}
			});
			f.insertAdjacentHTML('beforeend', str1);
			alert("파일 업로드가 완료되었습니다.");
			f.action = "/board/modify";
			console.log(f);
			f.submit();
		})
		.catch(err => console.error("파일 업로드 중 오류 발생:", err));
}

// 게시글 수정 -> 삭제 함수
function deleteBoard() {
	if (confirm("삭제하시겠습니까?")) {
		f.action = '/board/remove';
		f.submit();
	} else {
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

// 게시글 버튼 처리
document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) => {
		let type = btn.id;
		switch (type) {
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
	});
});

function openModal() {
	modal.style.display = "block";
}

function closeModal() {
	modal.style.display = "none";
}

function regReplyModalStyle() {
	addReplyBtn.classList.remove("hide");
	modifyReplyBtn.classList.add("hide");
	removeReplyBtn.classList.add("hide");
	inputReplyDate.closest('div').classList.add("hide");
	inputReplyer.removeAttribute('readonly');
}

function registerReply() {
	if (inputReply.value == '') {
		alert('내용을 입력해주세요.');
		return;
	}
	if (inputReplyer.value == '') {
		alert('작성자를 입력해주세요.');
		return;
	}
	rs.add({
		reply: inputReply.value,
		replyer: inputReplyer.value,
		bno: f.bno.value
	}, function (result) {
		console.log("result : " + result);
		if (result == "success") {
			alert("댓글이 등록되었습니다.");
		} else {
			alert("댓글 등록 실패");
		}
		closeModal();
		showList();
	});
}

function modifyReplyModalStyle() {
	modifyReplyBtn.classList.remove("hide");
	removeReplyBtn.classList.remove("hide");
	addReplyBtn.classList.add("hide");
	inputReplyDate.closest('div').classList.remove("hide");
	inputReplyer.setAttribute('readonly', true);
	inputReplyDate.setAttribute('readonly', true);
}

let rno;

function modifyModalPage(li) {
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

function modifyReply() {
	if (!inputReply.value) {
		alert("수정할 댓글을 입력하세요.");
		return;
	}
	console.log(rno);
	let rvo = { rno: rno, reply: inputReply.value };
	rs.update(rvo, function (result) {
		if (result != 1) {
			alert("댓글 수정 실패");
		} else {
			alert("댓글을 수정했습니다.");
		}
		showList();
		closeModal();
	});
}

function removeReply() {
	if (confirm('댓글을 삭제하시겠습니까?')) {
		rs.remove(rno, function (result) {
			if (result != 1) {
				alert("댓글 삭제 실패");
			} else {
				alert("댓글을 삭제했습니다.");
				showList();
				closeModal();
			}
		});
	}
}

// --------- 첨부 파일 스크립트 ---------
let uploadResult = document.querySelector(".uploadResult ul");
let existedFiles = []; // existedFile 배열 초기화

function showAttachList() {
    fetch('/board/getAttachList/' + f.bno.value)
        .then(response => response.json())
        .then(result => {
            let str = '';
            uploadResult.innerHTML = '';
            uploadResultArr = []; // 초기화
            console.log(result);
            result.forEach(file => {
                let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
                existedFiles.push({ uuid: file.uuid, fileName: file.fileName });
                uploadResultArr.push({ fileName: file.fileName, uploadPath: file.uploadPath, uuid: file.uuid }); // 기존 파일도 uploadResultArr에 추가
                str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
                str += "<a href='/download?fileName=" + fileCallPath + "'>";
                str += file.fileName;
                str += "</a>";
                str += `<span data-file="${fileCallPath}" style="display: none;"> X </span>`;
                str += "</li>";
            });
            uploadResult.innerHTML += str;
        })
        .catch(err => console.log(err));
}

// input type이 file에 변경점이 있을 때마다 이벤트 추가
let uploadResultArr = [];
let formData = new FormData(); // 처음부터 FormData를 생성하여 초기화
document.querySelector('input[type="file"]').addEventListener('change', () => {
    console.log("바꼈어요");
    formData = new FormData(); // 파일 선택 시 매번 새로운 FormData 생성
    const inputFile = document.querySelector('input[type="file"]');
    const files = inputFile.files;
    let fileAdded = false;

    for (let i = 0; i < files.length; i++) {
        if (isFileDuplicate(files[i].name)) {
            alert(`이미 같은 이름의 파일(${files[i].name})이 첨부되었습니다.`);
            continue; // 중복된 파일은 추가하지 않음
        }

        if (!checkExtension(files[i].name, files[i].size)) {
            return false; // return false의 경우 for문 종료 후 아래 코드 실행하지 않음
        }
        formData.append("uploadFile", files[i]);

        uploadResultArr.push({
            fileName: files[i].name,
        });
        fileAdded = true;
    }

    if (fileAdded) {
        showUploadFile(uploadResultArr);
    }
});

let deleteAttach = [];
uploadResult.addEventListener('click', (e) => {
    switch (e.target.tagName) {
        case 'SPAN':
            let targetFileName = e.target.closest('li').getAttribute('fileName');
            let uuid = e.target.closest('li').getAttribute('uuid');
            console.log(targetFileName);
            console.log(uuid);
            // 파일 이름 추출
            const fileNameToRemove = targetFileName.split('/').pop(); 

            // existedFiles에서 인덱스 찾기
            index = uploadResultArr.findIndex(file => file.fileName === fileNameToRemove);

            console.log(index);
            if (index !== -1) {
            	uploadResultArr.splice(index, 1); // uploadResultArr에서 해당 객체 제거
            } else {
                console.warn(`File with name ${fileNameToRemove}not found in arrays.`);
            }

            deleteAttach.push({ fileName: targetFileName, uuid: uuid });

            // li 요소 제거
            let li = e.target.closest('li');
            uploadResult.removeChild(li);

            // 파일 목록 갱신
            showUploadFile(uploadResultArr);
            break;
    }
});

function checkExtension(fileName, fileSize) {
	if (fileSize >= MAX_SIZE) {
		alert("파일 사이즈 초과");
		return false;
	}
	if (regex.test(fileName)) {
		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		return false;
	}
	return true;
}

// 파일 중복 여부 확인 함수
function isFileDuplicate(fileName) {
    return uploadResultArr.some(file => file.fileName === fileName);
}

function showUploadFile(uploadResultArr) {
    if (!uploadResultArr || uploadResultArr.length === 0) {
        uploadResult.innerHTML = "<li>첨부된 파일이 없습니다.</li>";
        return;
    }

    uploadResult.innerHTML = ""; // 기존 내용 초기화
    let str = '';
    uploadResultArr.forEach(file => {
        let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
        str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
        str += `<a href='/download?fileName="${fileCallPath}"'>${file.fileName}</a>`;
        str += `<span data-file="${fileCallPath}"> X </span>`;
        str += `</li>`;
    });
    uploadResult.innerHTML += str; // 새로 추가된 내용 표시
}

// UTC 시간을 KST로 변환하는 함수
function displayTime(unixTimeStamp) {
	// UTC 시간 문자열을 Date 객체로 변환
	const myDate = new Date(unixTimeStamp);

	const y = myDate.getFullYear();
	const m = String(myDate.getMonth() + 1).padStart(2, '0'); // 월을 두 자리로 맞춤
	const d = String(myDate.getDate()).padStart(2, '0'); // 일을 두 자리로 맞춤

	return `${y}-${m}-${d}`;
}

// 댓글 목록 가져오는 함수
function showList() {
	let bno = f.bno.value;
	let replyUL = document.querySelector(".chat");
	replyUL.innerHTML = ""; // 기존의 댓글 목록을 초기화
	
	let msg = '';
	rs.getList(bno, function(data) {
		if (data.length > 0) {
			data.forEach(vo => {
				msg += `<li data-rno=${vo.rno} onclick="modifyModalPage(this)">`;
				msg += `<div>`;
				msg += `<div class="chat-header">`;
				msg += `<strong class="primary-font">${vo.replyer}</strong>`;
				msg += `<small class="pull-right">${displayTime(vo.updateDate)}</small>`;
				msg += `</div>`;
				msg += `<p>${vo.reply}</p>`;
				msg += `</div>`;
				msg += `</li>`;
			});
		} else {
			msg += `<li data-rno=''>`;
			msg += `<div>`;
			msg += `<div class="chat-header">`;
			msg += `<strong class="primary-font"></strong>`;
			msg += `<small class="pull-right"></small>`;
			msg += `</div>`;
			msg += `<p>댓글이 없습니다.</p>`;
			msg += `</div>`;
			msg += `</li>`;
		}
		replyUL.innerHTML = msg;
	});
}