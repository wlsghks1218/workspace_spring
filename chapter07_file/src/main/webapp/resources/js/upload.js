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


// uploadBtn 함수 클릭 이벤트
document.getElementById("uploadBtn").addEventListener('click', ()=> {
	const formData = new FormData();
	const inputFile = document.querySelector('input[type=file]');
	const files = inputFile.files;
	for(let i=0; i < files.length; i++){
		
		if(!checkExtension(files[i].name, files[i].size)){
			return false; // return false의 경우 for문 종료 후 아래 코드 실행하지 않음
		}
		formData.append("uploadFile", files[i]);
	}
	
	fetch('/uploadAsyncAction', 
			{
				method : 'post',
				body : formData
			}
		)
		.then(response => response.json())
		.then(json => {
			console.log(json);
			showUploadFile(json);
			uploadDiv.replaceChild(cloneObj.cloneNode(true), uploadDiv.firstElementChild);
		})
		.catch(err => console.log(err));
})

// 첨부한 파일 목록	
let uploadResult = document.querySelector(".uploadResult ul")

function showUploadFile(uploadResultArr){
	if(!uploadResultArr || uploadResultArr.length == 0) return;
	
	
	let str = '';
	uploadResultArr.forEach( file => {
		let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName); // URL로 경로를 실어 보낼 때 알아서 변경해주는 것
		
		str += "<li>";
		str += "<a href='/download?fileName="+ fileCallPath +"'>";
		str += file.fileName;
		str += "</a>";
		str += `<span data-file=${fileCallPath}> X </span>`;
		str += "</li>";
	});
	uploadResult.innerHTML = str;
}

uploadResult.addEventListener('click', (e)=>{
	console.log(e.target.tagName);
	switch(e.target.tagName){
	case 'SPAN':
		let targetFile = e.target.getAttribute('data-file');
		console.log(targetFile);
		
		fetch('/deleteFile', 
				{
					method : 'post',
					body : targetFile,
					headers : {
						'Content-type' : 'text/plain'
					}
				}
			)
			.then(response => response.text())
			.then(result => {
				console.log(result);
				if(result == "deleted"){
					let liEle = e.target.closest('li');
					uploadResult.removeChild(liEle);
				}
			})
			.catch(err => console.log(err));
		break
	}
})