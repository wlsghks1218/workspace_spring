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

//로그인 페이지로 이동
function loginPage() {
    location.href = '/board/login'; 
}

//회원가입 페이지로 이동
function joinPage() {
    location.href = '/board/signIn'; 
}

function logout() {
    // 로그아웃 후 customLogout 페이지로 이동
    location.href = '/customLogout'; // 필요한 URL로 변경하세요.
}