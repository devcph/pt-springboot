package com.coderipperp.pt.web;

import com.coderipperp.pt.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//////////////////////////////////////////////////////
// *@RunWith(SpringRunner.class) -
// 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
// 스프링 부트 테스트와 JUnit 사이의 연결자 역할
// 여기서는 SpringRunner 스프링 실행자를 사용
//
// *@WebMvcTest
// 스프링 여러 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
// @Service, @Component, @Repository 등은 사용 불가
// 컨트롤러만 사용하기 때문에 선언
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {
    ////////////////////////////////////////
    // *@Autowired
    // 스프링이 관리하는 빈(Bean)을 주입받음
    //
    // *private MockMvc mvc
    // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API를 테스트 할 수 있음
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        ////////////////////////////////////////
        // *mvc.perform(get("/hello"))
        // MockMvc를 통해 /hello 주소로 HTTP GET 요청
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 가능
        //
        // *private MockMvc mvc
        // 웹 API를 테스트할 때 사용
        // 스프링 MVC 테스트의 시작점
        // 이 클래스를 통해 HTTP GET, POST 등에 대한 API를 테스트 할 수 있음
        //
        // *.andExpect(status().isOk())
        // mvc.perform의 결과를 검증
        // HTTP Header의 Status를 검증
        // StatusCode (200, 404, 500 등) 상태를 검증
        // isOk는 200인지 아닌지를 검증하는 것
        //
        // *.andExpect(content().string(hello))
        // mvc.perform의 결과를 검증
        // 응답 본문의 내용을 검증
        // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        //////////////////////////////////////////////////////
        // *param
        // API 테스트할 때 사용될 요청 파라미터 설정
        // 값은 String만 허용
        // 날짜/숫자 등의 데이터도 등록할 때엔 문자열로 변경해야만 가능
        //
        // *jsonPath
        // JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $를 기준으로 필드명을 명시
        // 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
