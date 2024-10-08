console.log("reply js.......");
var xhr = new XMLHttpRequest();
let bno = new URLSearchParams(location.search).get('bno');

// reply에는 insert를 위한 ReplyVO 객체를 담는다.
// callback에 응답 받은 데이터를 담는다.

const replyService = (function(){
	function add(reply, callback){
		fetch('/reply/new', 
				{
					method : 'post',
					body : JSON.stringify(reply),
					headers : {'Content-type':'application/json; charset=utf-8'}
				})
			.then(response => response.text())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	
	function getList(bno, callback){
		fetch('/reply/pages/'+ bno + '.json')
			.then(response => response.json())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	
	function remove(rno, callback){
		fetch('/reply/'+ rno, 
				{
					method : 'delete',
				})
			.then(response => response.text())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	function update(reply, callback){
		fetch('/reply/'+ reply.rno,
				{
					method : 'put',
					body : JSON.stringify(reply),
					headers : {'Content-type':'application/json; charset=utf-8'}
				})
			.then(response => response.text())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	function get(rno, callback){
		fetch('/reply/'+ rno) 
			.then(response => response.json())
			.then(data => {
				callback(data);
			})
			.catch(err => console.log(err));
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
})();
