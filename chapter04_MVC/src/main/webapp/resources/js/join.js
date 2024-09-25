function backToHistory(){
	history.back();
}

const f = document.forms[0];


document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) => {
		let type = btn.id;
		switch (type) {
			case "submitBtn":
				e.preventDefault();
				signInChk();
				break;
		}
	});
});

function signInChk(){
	console.log(f.userId.value);
	console.log(f.userPw.value);
	console.log(f.userName.value);
	if(f.userId.value != null && f.userPw.value != null && f.userName.value != null){
		console.log("gogo");
		f.action = "/member/signIn";
		f.submit();
	}else{
		console.log("문제가 있어요");
	}
}