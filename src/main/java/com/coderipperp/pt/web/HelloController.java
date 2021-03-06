package com.coderipperp.pt.web;

import com.coderipperp.pt.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller를 JSON을 반환하는 컨트롤러로 만들어줌.
public class HelloController {
    //////////////////////////////////////////////////////
    // *GetMapping -
    // HTTP Method인 Get의 요청을 받을 수 있는 API를 맹글어줌
    // /hello로 요청이 들어오면 문자열 hello를 반환하는 기능을 가짐
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    //////////////////////////////////////////////////////
    // *RequestParam
    // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    // 여기서는 외부에서 name(@RequestParam("name"))이란 이름으로
    // 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
    public HelloResponseDto helloDto(
            @RequestParam("name") String name,
            @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
