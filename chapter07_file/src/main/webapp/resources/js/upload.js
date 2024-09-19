// 확장자 정규식
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
// 파일크기 제한 - 5MB
const MAX_SIZE = 5242880;

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
		
		formData.append("uploadFile", files[i]);
	}
	
	fetch('/uploadAsyncAction', 
			{
				method : 'post',
				body : formData
			}
		)
		.then(response => response.text())
		.then(text => {
			console.log(text);
		})
		.catch(err => console.log(err));
})