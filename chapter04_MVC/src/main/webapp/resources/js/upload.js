// 비어있는 요소 복사
let uploadDiv = document.querySelector(".uploadDiv");
// cloneNode(true) : true -> 하위 노드까지 복사할 것인지
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);


// 확장자 정규식
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
// 파일크기 제한 - 5MB
const MAX_SIZE = 5242880;

// 첨부파일 예외처리
function checkExtension(fileName, fileSize){
	if(fileSize >= MAX_SIZE){
		alert("파일 사이즈 초과");
		return false;
	}
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드할 수 없습니다.")
		return false;
	}
	return true;
}


// input type이 file에 변경점이 있을 때마다 이벤트 추가
let uploadResultArr = [];
let formData = null;
document.querySelector('input[type="file"]').addEventListener('change', ()=> {
	console.log("바꼈어요")
	formData = new FormData();
	const inputFile = document.querySelector('input[type="file"]');
	const files = inputFile.files;
	let fileAdded = false;
	
	for(let i=0; i < files.length; i++){
        if (isFileDuplicate(files[i].name)) {
            alert(`이미 같은 이름의 파일(${files[i].name})이 첨부되었습니다.`);
            continue; // 중복된 파일은 추가하지 않음
        }
		
		if(!checkExtension(files[i].name, files[i].size)){
			return false; // return false의 경우 for문 종료 후 아래 코드 실행하지 않음
		}
		formData.append("uploadFile", files[i]);
		
		uploadResultArr.push({
			fileName: files[i].name,
		});
		fileAdded = true;
	}
	
	if(fileAdded){
	showUploadFile(uploadResultArr);
	}
})


// 첨부한 파일 목록	
let uploadResult = document.querySelector(".uploadResult ul")
function showUploadFile(uploadResultArr){
	if(!uploadResultArr || uploadResultArr.length == 0) return;
	
	let str = '';
	uploadResult.innerHTML = '';
	
	uploadResultArr.forEach( file => {
		str += `<li>`;
		str += `<a>${file.fileName}</a>`;
		str += `<span data-file="${file.fileName}"> X </span>`;
		str += "</li>";
	});
	uploadResult.innerHTML += str;
}

//파일 중복 여부 확인 함수
function isFileDuplicate(fileName) {
    return uploadResultArr.some(file => file.fileName === fileName);
}

uploadResult.addEventListener('click', (e) => {
    console.log(e.target);
    switch (e.target.tagName) {
        case 'SPAN':
            let targetFileName = e.target.getAttribute('data-file');

            // 배열에서 해당 파일 이름의 객체를 제거
            const index = uploadResultArr.findIndex(file => file.fileName === targetFileName);
            if (index !== -1) {
                uploadResultArr.splice(index, 1); // 해당 객체 제거
            }

            // formData에서도 해당 파일 제거
            const inputFile = document.querySelector('input[type="file"]');
            const files = inputFile.files;
            const newFormData = new FormData(); // 새로운 FormData 생성

            for (let [key, value] of formData.entries()) {
                if (value.name !== targetFileName) {
                    newFormData.append(key, value); // 기존 파일 중 제거되지 않은 파일만 추가
                }
            }
            formData = newFormData; // formData를 업데이트

            // li 요소 제거
            let liEle = e.target.closest('li');
            uploadResult.removeChild(liEle);

            // 파일 목록 갱신
            showUploadFile(uploadResultArr);
            break;
    }
});