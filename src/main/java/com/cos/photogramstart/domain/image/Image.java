package com.cos.photogramstart.domain.image;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption;				// 오늘 나 너무 피곤해!
	
	private String postImageUrl;		// 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	
	@JsonIgnoreProperties({"images"}) 	// User 객체 내 images는 무시한다.
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	//이미지 좋아요
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image") // mappedBy = "image" -> Likes 객체의 변수 image 
	private List<Likes> likes;
	
	//댓글
	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image")
	private List<Comment> comments;
	
	private LocalDateTime createDate;
	
	// import javax.persistence.Transient;
	@Transient // DB에 컬럼이 만들어지지 않는다.
	private boolean likeState;
	
	@Transient // DB에 컬럼이 만들어지지 않는다.
	private int likeCount;
	
	@PrePersist
	public void create() {
		this.createDate = LocalDateTime.now();
	}

	
	
	
	
	
	
	
	
	
// Image를 리턴할때 Image 내부의 Likes 내부의 Image를 리턴하지 않게 해야한다!!!!!
// Image -> Likes -> Image -> Likes -> ...
// 무한참조가 발생하기 때문에 이를 막기 위해서는 @JsonIgnoreProperties({"image"})를 사용한다!!!!!
	
	
	
	
	
	
	
	
	
	
	
	
/** 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않도록 함!!!!!	**********************************************************************/
	
// 순서 (1)	
// @Data 어노테이션을 사용하면 자동으로 toString() 함수가 생성된다!!!
// 사진 업로드 시 ImageService 내에 imageEntity를 출력할때 왜 오류가 나는지 확인하면서 toString() 함수를 만든 것이다...
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl + ", user=" + user
//				+ ", createDate=" + createDate + "]";
//	}
		

// 순서 (2)
// + ", user=" + user 제거하면 해결완료!!!!!
//	@Override
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl 
//				+ ", createDate=" + createDate + "]";
//	}

	
/*****************************************************************************************************************************/
}

