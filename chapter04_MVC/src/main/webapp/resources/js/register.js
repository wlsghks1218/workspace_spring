const CSS_FILE_PATH = '/resources/css/register.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);

const f = document.forms[0];

function register(){
	if(f.title.value==""){
		alert("제목을 입력하세요.")
		return;
	}
	if(f.writer.value==""){
		alert("작성자를 입력하세요.")
		return;
	}
	if(f.content.value==""){
		alert("내용을 입력하세요.")
		return;
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
        let str = '';
        json.forEach( (file, index) => {
            if (file.uploadPath && file.uuid && file.fileName) { // 값이 비어있지 않은 경우에만 추가
                str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${file.uploadPath}"/>`;
                str += `<input type="hidden" name="attachList[${index}].uuid" value="${file.uuid}"/>`;
                str += `<input type="hidden" name="attachList[${index}].fileName" value="${file.fileName}"/>`;
            }
        });

        f.insertAdjacentHTML('beforeend', str);
        alert("파일 업로드가 완료되었습니다.");
        
        // 이후에 다른 처리 필요 시 여기서 추가
        f.action = '/board/register';
        console.log(f);
        f.submit();
    })
    .catch(err => console.error("파일 업로드 중 오류 발생:", err));
}

document.querySelectorAll(".panel-body-btns button").forEach(btn => {
	btn.addEventListener('click', ()=>{
		let type = btn.id;
		console.log(type);
		switch(type){
		case "registerBtn":
			register();
			break;
		case "resetBtn":
			f.reset();
			break;
		case "indexBtn":
			let pageData = getStorageData();
			let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`;
			location.href = `/board/list?${sendData}`;
			break;
		}
	})
})
