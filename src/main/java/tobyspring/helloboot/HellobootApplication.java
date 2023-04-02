package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너 만들기
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class); // HelloController 클래스 정보를 넘겨 스프링 빈으로 등록해주기
        applicationContext.registerBean(SimpleHelloService.class); // SimpleHelloService도 빈으로 등록해주기

        applicationContext.refresh(); // 스프링 컨테이너가 초기화 될 때 빈들을 만들어줌

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // 매핑
            servletContext.addServlet("dispatcherServlet",
                new DispatcherServlet(applicationContext)
                ).addMapping("/*"); // 매핑되는 url -> 해당 url로 요청이 들어오면 "hello" 서블릿을 수행
        });
        webServer.start();

    }

}
