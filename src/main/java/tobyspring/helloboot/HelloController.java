package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    // 생성자 파라미터로 의존성 주입
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody // String으로 반환하는 값을 view 호출이 아닌, responsebody로 데이터를 넘겨주기 위해 사용 (RestController 어노테이션을 사용하면, method에 @ResponseBody를 사용하지 않아도 됨)
    public String hello(String name) { // 쿼리스트링으로 받을 파라미터
        // 유저의 요청사항 검증 (파라미터 체크 - Objects.requireNonNull)
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
