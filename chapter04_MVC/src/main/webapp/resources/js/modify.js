const CSS_FILE_PATH = '/resources/css/modify.css';
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
document.head.appendChild(linkEle);
const f = document.forms[0];

document.querySelectorAll(".panel-body-btns button").forEach(btn => {
	btn.addEventListener('click', ()=>{
		let type = btn.id;
		switch(type){
		case "modifyBtn":
			f.action = "/board/modify";
			f.submit();
			break;
		case "indexBtn":
			location.href="/board/list"
			break;
		}
	})
})