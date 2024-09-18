package com.cos.photogramstart.domain.subscribe;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity	// ORM(Obejct-Relational Mapping)

@Table(
		uniqueConstraints = {
			@UniqueConstraint(
						name = "subscrube_uk",
						columnNames = {"fromUserId" , "toUserId"}
					)	
		}
)
public class Subscribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	/*
	 * @JoinColumn 어노테이션을 사용하여
	 * 카멜 표기법(Camel Case)로 변경해준다. 
	 * 변경하기전에는 스네이크 표기법(Snake Case)으로 만들어졌다. 
	 * from_user, to_user
	 */
	@JoinColumn(name = "fromUserId")
	@ManyToOne
	private User fromUser;
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser;
	
	
	private LocalDateTime createDate;
	
	@PrePersist // 디비에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}

/*
 * 카멜 표기법 (Camel Case)
 * 
 * 카멜 표기법(Camel Case)은 프로그래밍 및 식별자 이름 작성 규칙 중 하나로, 
 * 다양한 프로그래밍 언어 및 플랫폼에서 사용되는 네이밍 규칙 중 하나입니다. 
 * 카멜 표기법의 주요 특징은 다음과 같습니다:
 * 
 * 1. 단어의 첫 글자는 소문자로 시작합니다.
 * 2. 두 번째 단어부터는 첫 글자를 대문자로 표기합니다.
 * 3. 단어 사이에는 공백이나 특수 문자가 없고, 단어들을 연결하여 작성합니다.
 * 
 * 카멜 표기법은 변수 이름, 메서드 이름, 클래스 이름, 파일 이름 등 식별자를 만들 때 주로 사용됩니다. 다음은 몇 가지 예시입니다:
 * 변수 이름: myVariable, counterValue, userName
 * 메서드 이름: calculateTotalAmount(), getUserInfo(), openFileInputStream()
 * 클래스 이름: MyClass, UserService, HTTPRequestHandler
 * 파일 이름: myFile.java, myDocument.docx, myImage.jpg
 * 
 * 
 * 
 * 
 * 파스칼 표기법 (Pascal Case)
 * 
 * 파스칼 표기법(Pascal Case)은 프로그래밍 및 식별자 이름 작성 규칙 중 하나로, 
 * 다양한 프로그래밍 언어 및 플랫폼에서 사용되는 네이밍 규칙 중 하나입니다. 
 * 파스칼 표기법의 주요 특징은 다음과 같습니다:
 * 
 * 1. 모든든 단어의 첫 글자를 대문자로 시작합니다.
 * 2. 단어 사이에는 공백이나 특수 문자가 없고, 단어들을 연결하여 작성합니다.
 * 
 * 파스칼 표기법은 주로 클래스 이름과 타입 이름을 작성할 때 사용됩니다. 다음은 몇 가지 예시입니다:
 * 클래스 이름: MyClass, CarModel, UserProfile
 * 타입 이름: Integer, StringBuffer, LinkedListNode
 * 
 * 
 * 
 * 
 * 스네이크 표기법 (Snake Case)
 * 
 * Snake case(스네이크 케이스)는 변수, 함수, 데이터베이스 테이블 또는 열의 이름을 작성할 때 사용되는 명명 규칙 중 하나입니다. Snake case는 다음과 같은 특징을 가집니다:
 * 
 * 단어 구분: Snake case에서는 단어와 단어 사이를 밑줄(_)로 구분합니다. 각 단어는 소문자로 작성하며, 밑줄로 연결됩니다.
 * 모든 문자는 소문자: Snake case에서는 모든 글자를 소문자로 작성합니다. 대문자를 사용하지 않고 모든 단어가 소문자로 작성되므로 가독성이 좋습니다.
 * 주로 데이터베이스에서 사용: Snake case는 데이터베이스 테이블 이름과 열 이름을 작성할 때 주로 사용됩니다. 
 * 데이터베이스 테이블 및 열은 스네이크 케이스로 작성되며, 데이터베이스와 어플리케이션 코드 간의 일관성을 유지하기 위해 사용됩니다.
 * 
 * Snake case의 예시:
 * 데이터베이스 테이블 및 열: user_profile, first_name, phone_number
 * 변수 또는 함수 이름: total_score, calculate_average, is_active
 * Snake case는 가독성이 좋고 언더스코어를 사용하여 단어를 명확하게 구분하기 때문에 코드 또는 데이터베이스 스키마를 읽고 이해하기 쉽습니다.
 * Snake case는 주로 데이터베이스와의 통합 또는 프로젝트에서 코드 작성의 일관성을 유지하기 위해 사용됩니다.
 */
