// (1) 회원정보 수정
function update(userid, event) {
	
	event.preventDefault(); // form 태그 액션을 막는다!!!
	
	/*
		header.jsp 파일에 제이쿼리 선언해놓음
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	*/
	let data = $("#profileUpdate").serialize();
	/*
		#profileUpdate는 update.jsp 파일에 id값이다.
		$("#profileUpdate").serialize();
		form 태그 id 속성값이 profileUpdate인 form 데이터들을 모두 불러낼 수 있다.
	*/
	
	//console.log(data);
	
	
	
	//$.ajax().done().fail();
	//$.ajax({}).done({res=>}).fail({error=>}); -> 화살표 함수 사용
	$.ajax({
		// 자바스크립트 Obejct를 넣는다.
		type : "put",
		url : `/api/user/${userid}`, //백틱(`) 사용
		data : data,
		contextType : "application/x-www-form-urlencoded : charset=utf-8",
		dataType : "json"
	})
	.done(res=>{ // HttpStatus 상태코드 200번대
		//console.log("성공");
		//location.href = `/user/${userid}`;
		
		
		console.log("성공", res);
		location.href = `/user/${userid}`;
		
	})
	.fail(error=>{ // HttpStatus 상태코드 200번대가 아닐 때
		//console.log("실패");
		
		
		//console.log("실패", error.responseJSON.data);
		//alert(error.responseJSON.data.name);
		
		
		console.log("실패");
		alert(JSON.stringify(error.responseJSON.data));
		/*
		 * JSON.stringify()
		 * 자바스크립트 Object를 JSON 문자열로 변경해준다.
		 */
	});
	
}








/*function update(userid) {
	
	
	
		header.jsp 파일에 제이쿼리 선언해놓음
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	let data = $("#profileUpdate").serialize();
	
		#profileUpdate는 update.jsp 파일에 id값이다.
		$("#profileUpdate").serialize();
		form 태그 id 속성값이 profileUpdate인 form 데이터들을 모두 불러낼 수 있다.
	
	
	//console.log(data);
	
	
	
	//$.ajax().done().fail();
	//$.ajax({}).done({res=>}).fail({error=>}); -> 화살표 함수 사용
	$.ajax({
		// 자바스크립트 Obejct를 넣는다.
		type : "put",
		url : `/api/user/${userid}`, //백틱(`) 사용
		data : data,
		contextType : "application/x-www-form-urlencoded : charset=utf-8",
		dataType : "json"
	})
	.done(res=>{	
		console.log("성공");
		location.href = `/user/${userid}`;
	})
	.fail(error=>{
		console.log("실패");
	});
	
}*/








/*
	function update(userid, event) {
	
	//alert("제출버튼 클릭 시 여기까지 오니?");
	
	console.log("event : " , event)
}
*/
