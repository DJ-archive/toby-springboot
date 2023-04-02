package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) { // 쿼리스트링으로 받을 파라미터
        SimpleHelloService helloService = new SimpleHelloService();
        // 유저의 요청사항 검증 (파라미터 체크 - Objects.requireNonNull)
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
