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
	let str ='';
	document.querySelectorAll('.uploadResult ul li').forEach( (li, index) => {
		let path = li.getAttribute('path');
		let uuid = li.getAttribute('uuid');
		let fileName = li.getAttribute('fileName');
		
		str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}"/>`;
		str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}"/>`;
		str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}"/>`;
	})
//	f.innerHTML += str; // 입력된 form 데이터가 다 날아감
	f.insertAdjacentHTML('beforeend', str);
	f.action = '/board/register';
	f.submit();
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
