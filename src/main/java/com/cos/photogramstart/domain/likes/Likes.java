package com.cos.photogramstart.domain.likes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MySQL이나 MariaDB는 like가 키워드이기 때문에 like로 테이블이 생성되지 않는다!!!

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
(
		uniqueConstraints = 
		{
				@UniqueConstraint
				(
						name = "likes_uk"
						, columnNames = {"imageId", "userId"}
				)
		}
)
public class Likes { // N
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "imageId")	// Like 테이블의 컬럼 이름을 image가 아닌 imageId로 만들어라
	@ManyToOne // @ManytoOne은 Eager 전략이고 @OnetoMany는 Lazy 전략이다.
	private Image image; // 1 
	
	// 오류가 터지고 나서 잡아봅시다.
	@JoinColumn(name = "userId") // Likes 테이블의 컬럼 이름을 user가 아닌 userId로 만들어라 
	@ManyToOne
	private User user; // 1
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
