package com.coderipperp.pt.config.auth.dto;

import com.coderipperp.pt.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

////////////////////////////////////////////////////////
// 기존의 User는 Entity이기 때문에, 직렬화하면 안됨.
// 엔티티를 직렬화할 경우, 엔티티의 새로운 관계 형성에 따라 이슈가 발생할 가능성 높음
// 그러므로, 직렬화 기능을 가진 세션 Dto를 추가
// 인증된 사용자 정보만 필요.

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
