package com.coderipperp.pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 프로젝트 메인 클래스
 * <p>'@SpringBootApplication'으로 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정.</p>
 * <p>main 메소드로 실행하는 SpringApplication.run으로 내장 WAS(Web Application Server)를 실행.</p>
 * <p>※ 내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행함을 의미.</p>
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
