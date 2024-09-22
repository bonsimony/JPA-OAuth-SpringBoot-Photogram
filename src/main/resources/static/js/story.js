/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

//(0) 현재 로그인한 사용자 아이디
let principalId = $("#principalId").val();

//alert(principalId);

//(1) 스토리 로드하기

let page = 0;

function storyLoad() {
	$.ajax({
		// get은 디폴트이기 때문에 type : get은 넣지 않아도 된다!!!
		url : `/api/image?page=${page}`
		, dataType : "json" 
	}).done(res =>{
		console.log(res);
		
		res.data.content.forEach((image)=>{
			let stroyItem = getStoryItem(image);
			$("#storyList").append(stroyItem);
		});
		
//		res.data.forEach((image)=>{
//			let stroyItem = getStoryItem(image);
//			$("#storyList").append(stroyItem);
//		});
		
	}).fail(error =>{
		console.log("오류", error);
	});
}






//(1) 스토리 로드하기
//function storyLoad() {
//	$.ajax({
//		// get은 디폴트이기 때문에 type : get은 넣지 않아도 된다!!!
//		url : `/api/image`
//		, dataType : "json" 
//	}).done(res =>{
//		console.log(res);
//		
//		res.data.content.forEach((image)=>{
//			let stroyItem = getStoryItem(image);
//			$("#storyList").append(stroyItem);
//		});
//		
//		res.data.forEach((image)=>{
//			let stroyItem = getStoryItem(image);
//			$("#storyList").append(stroyItem);
//		});
//		
//	}).fail(error =>{
//		console.log("오류", error);
//	});
//}

storyLoad();

function getStoryItem(image) {
	let item = `<div class="story-list__item">
	
	<div class="sl__item__header">
		<div>
			<img class="profile-image" src="/upload/${image.user.profileImageUrl}"
				onerror="this.src='/images/person.jpeg'" />
		</div>
		<div>${image.user.username}</div>
	</div>

	<div class="sl__item__img">
		<img src="/upload/${image.postImageUrl}" />
	</div>

	<div class="sl__item__contents">
		<div class="sl__item__contents__icon">

			<button>`;
				
				
				if(image.likeState){
					item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
				}else
				{
					item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
				}
				
				/*<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>*/
				
				
				
		item += `
			</button>
		</div>

		<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount} </b>likes</span>

		<div class="sl__item__contents__content">
			<p>${image.caption}</p>
		</div>

		<div id="storyCommentList-${image.id}">`;



		image.comments.forEach((comment)=>{
			item += `<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
				<p>
					<b>${comment.user.username}</b> ${comment.content}
				</p>`;
			
			
				
				if(principalId == comment.user.id){
					
					item += 
					`
						<button onclick = "deleteComment(${comment.id})">
							<i class="fas fa-times"></i>
						</button>
					`;
				}
				
				
			
			
			
			
			item +=`
			</div>`;
			
		}); 
		
			


	item += `
		</div>

		<div class="sl__item__input">
			<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
			<button type="button" onClick="addComment(${image.id})">게시</button>
		</div>

	</div>
</div>`;
	return item;
}






// (1) 스토리 로드하기
//function storyLoad() {
//
//}

//function getStoryItem() {
//
//}









// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	//console.log("스크롤중");
	//console.log("윈도우 scrollTop", $(window).scrollTop());
	//console.log("문서의높이", $(document).height());
	//console.log("윈도우 높이", $(window).height());
	
	let checkNum =  $(window).scrollTop() - ($(document).height() - $(window).height())
	console.log(checkNum);
	
	if(checkNum < 1 && checkNum > -1){
		page++;
		storyLoad();
	}
	
});





// (2) 스토리 스크롤 페이징하기
//$(window).scroll(() => {
//
//});










// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	if (likeIcon.hasClass("far")) { // 좋아요를 하겠다.
		
		
		
		// $.ajax().done().fail();
		$.ajax({
			type : "post"
			, url : `/api/image/${imageId}/likes`
			, dataType : "json" // 리턴값은 JSON 형태로 하겠다!!!!
		}).done(res=>{
			
			
			
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			// console.log("좋아요 카운트", likeCountStr);
			
			
			let likeCount = Number(likeCountStr) + 1;
			//console.log("좋아요 카운트 증가", likeCount);
			
			
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			
			
			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error=>{
			console.log("오류",error);
		});
		
	} else { //좋아요를 취소 하겠다.
		likeIcon.removeClass("fas");
		
		//$.ajax().done().fail();
		$.ajax({
			type : "delete"
			, url : `/api/image/${imageId}/likes`
		}).done(res =>{
			
			
			
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1;
			$(`#storyLikeCount-${imageId}`).text(likeCount);
			
			
			
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error =>{
			console.log("오류",error);
		});
		
		
		
		
		
	}
}






// (3) 좋아요, 안좋아요
//function toggleLike() {
//	let likeIcon = $("#storyLikeIcon-1");
//	if (likeIcon.hasClass("far")) {
//		likeIcon.addClass("fas");
//		likeIcon.addClass("active");
//		likeIcon.removeClass("far");
//	} else {
//		likeIcon.removeClass("fas");
//		likeIcon.removeClass("active");
//		likeIcon.addClass("far");
//	}
//}













function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

	let data = {
		imageId : imageId
		, content : commentInput.val()
	}
	
	
	//console.log(data);
	//console.log(JSON.stringify(data));
	
	//return;
	
	//alert(data.content);
	//return;
	
	
	
	/****************************************************** 
	* AOP 처리를 하기 위해 ValidationAdvice 파일 생성 후 BindingResult 잘 작동하는지 확인시에 주석했었음
	******************************************************/
	
	if(data.content === ""){
		alert("댓글을 작성해주세요!");
		return;
	}
	
	/******************************************************/
	
	
	
	
	//$.ajax().done().fail();
	$.ajax({
		type : "post"
		, url : "/api/comment/"
		, data : JSON.stringify(data)
		, contentType : "application/json;charset=urf-8"
		, dataType : "json"
	}).done(res=>{ //  res에는 항상 통신의 결과가 담긴다!!!
		//console.log("성공", res);
		
	let comment = res.data;	
		
	let content = 
	      `
		  <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
		   
		    	<p>
		      		<b>${comment.user.username}</b>
		     		${comment.content}
		    	</p>
		    
		    
		 
		    	<button onclick = "deleteComment(${comment.id})">
		    		<i class="fas fa-times"></i>
		    	</button>
		    	
		  	</div>
		  		
			`;
	
	commentList.prepend(content); // prepend는 화면에서 데이터가 위로 쌓인다 -> 최신 데이터가 위에 위치한다.
												   // append는 화면에서 데이터가 아래로 쌓인다 -> 오래된 데이터가 위에 위치한다.
											   
											   
											   
	}).fail(error=>{
		//console.log("오류", error.responseJSON);
		//console.log("오류", error.responseJSON.message);
		//console.log("오류", error.responseJSON.data.content);
		
		alert(error.responseJSON.data.content);
		
	});

	
	commentInput.val(""); // 인풋 필드를 깨끗하게 비워준다.
}






// (4) 댓글쓰기
//function addComment() {
//
//	let commentInput = $("#storyCommentInput-1");
//	let commentList = $("#storyCommentList-1");
//
//	let data = {
//		content: commentInput.val()
//	}
//
//	if (data.content === "") {
//		alert("댓글을 작성해주세요!");
//		return;
//	}
//
//	let content = `
//			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
//			    <p>
//			      <b>GilDong :</b>
//			      댓글 샘플입니다.
//			    </p>
//			    <button><i class="fas fa-times"></i></button>
//			  </div>
//	`;
//	commentList.prepend(content);
//	commentInput.val("");
//}











// (5) 댓글 삭제
function deleteComment(commentId) {
	// $.ajax().done().fail();
	
	$.ajax({
		type : "delete"
		, url : `/api/comment/${commentId}`
	}).done(res=>{
		console.log("성공", res);
		
		$(`#storyCommentItem-${commentId}`).remove();
		
	}).fail(error=>{
		console.log("실패", error);
	});
	
}






// (5) 댓글 삭제
//function deleteComment() {
//
//}







