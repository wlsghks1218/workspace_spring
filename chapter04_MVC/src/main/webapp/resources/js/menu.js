document.querySelectorAll('.menu a').forEach(a => {
	a.addEventListener('click', e => {
		e.preventDefault();
		let menu = a.getAttribute('href');
		
		switch (menu) {
		case "boardList":
			location.href = "/board/list";
			break;
		}
	})
})

document.querySelectorAll('.header-title a').forEach(a => {
	a.addEventListener('click', e => {
		e.preventDefault();
		let type = a.getAttribute('href');
		
		switch (type) {
		case "mainPage":
			location.href = "/";
			break;
		}
	})
})

function setStorageData(pageNum, amount){
	let pageData = {
			pageNum : pageNum,
			amount : amount
	};
	localStorage.setItem('page_data', JSON.stringify(pageData));
}
function getStorageData(){
	return JSON.parse(localStorage.getItem('page_data'));
}