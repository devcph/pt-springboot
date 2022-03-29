package com.coderipperp.pt.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// ※ Entity 클래스에서는 절대로 Setter 메소드를 만들지 않는다.
// 만약 필드의 갱신이 필요하다면, 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제
@Getter // 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor // 기본 생성자 자동 추가. (public Posts(){}와 같은 효과)
@Entity // 테이블과 링크될 클래스임을 명시. 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭(예: FileManager -> file_manager table)
public class Posts {
    ////////////////////////////////////////////////////////
    // *@Id
    // 해당 테이블의 PK 필드를 나타냄
    // ※ Entity의 PK는 Long 타입의 Auto_increment를 추천
    // 비즈니스상의 유니크 키나 복합 키로 PK를 잡을 경우 수정 귀찮아짐.
    // 유니크 키나 복합 키는 그냥 따로 추가.
    //
    // *@GeneratedValue
    // PK 생성 규칙을 나타냄.
    // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    ////////////////////////////////////////////////////////
    // *@Column
    // 테이블의 칼럼을 나타나며, 굳이 사용하지 않아도 됨
    // 하지만 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있을 때

    @Column(length = 500, nullable = false) // VARCHAR(255) => VARCHAR(500)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // VARCHAR => TEXT
    private String content;

    private String author;

    ////////////////////////////////////////////////////////
    // *@Builder
    // 해당 클래스의 빌더 패턴 클래스 생성
    // 생성자 상단 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
