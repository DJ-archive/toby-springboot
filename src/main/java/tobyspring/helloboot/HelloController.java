package tobyspring.helloboot;

import java.util.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext;
    // 생성자가 완료되는 시점까지는 초기화가 되어야하는데,생성자를 통해서 인스턴스가 만들어진 이후에 호출되기 때문에 final로 지정할 수 없다.

    // 생성자 파라미터로 의존성 주입
    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }

    @GetMapping("/hello")
    public String hello(String name) { // 쿼리스트링으로 받을 파라미터
        // 유저의 요청사항 검증 (파라미터 체크 - Objects.requireNonNull)
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
