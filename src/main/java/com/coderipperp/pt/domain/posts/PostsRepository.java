package com.coderipperp.pt.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

////////////////////////////////////////////////////////
// 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면
// 기본적인 CRUD 메소드가 자동으로 생성됨
// @Repository를 추가할 필요도 없음. 단, Entity 클래스와 기본 Entity Repository는 함께 위치하여야 함
// Entity 클래스는 Repository가 없이는 제대로 역할을 할 수 없기 때문

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJpa에서 제공하지 않는 메소드는 쿼리로 작성해도 됨
    // @Query 가 가독성이 좋으니, 뭐 알아서 선택해서 사용
    // ※ 규모가 커질수록 조회용 프레임워크 권장됨
    // querydsl 이 추천됨
    // 1. 타입 안정성 보장
    // 2. 다수 회사에서 사용
    // 3. 레퍼런스 많음
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
