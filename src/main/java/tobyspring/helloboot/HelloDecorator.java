package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service // component도 가능. 빈으로 등록.
@Primary // HelloService 빈이 두 개이므로 우선적으로 Decorator를 통해 HelloService 빈을 사용하도록 함
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
