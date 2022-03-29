package com.coderipperp.pt.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

////////////////////////////////////////////////////////
// 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면
// 기본적인 CRUD 메소드가 자동으로 생성됨
// @Repository를 추가할 필요도 없음. 단, Entity 클래스와 기본 Entity Repository는 함께 위치하여야 함
// Entity 클래스는 Repository가 없이는 제대로 역할을 할 수 없기 때문

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
