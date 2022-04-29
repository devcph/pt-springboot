package com.coderipperp.pt.web;

import com.coderipperp.pt.config.auth.LoginUser;
import com.coderipperp.pt.config.auth.dto.SessionUser;
import com.coderipperp.pt.service.posts.PostsService;
import com.coderipperp.pt.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    //////////////////////////////////////////////////////
    // * Model
    // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
    // 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달

    // @LoginUser
    // 기존에 (User) httpSession.getAttribute("user")로 가져오던 정보 값이 개선
    // 어느 컨트롤러든 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //////////////////////////////////////////////////////
        // 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        // 즉, 로그인 성공 시 httpSisson.getAttribute("user")에서 값을 가져올 수 있음
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        //////////////////////////////////////////////////////
        // 세션에 저장된 값이 있을 때만 model에 userName으로 등록
        // 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
