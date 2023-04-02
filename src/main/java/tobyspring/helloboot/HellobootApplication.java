package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // @Component가 붙은 클래스들을 빈으로 등록해준다.
public class HellobootApplication {

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    // 매핑
                    servletContext.addServlet("dispatcherServlet",
                        new DispatcherServlet(this)
                    ).addMapping("/*"); // 매핑되는 url -> 해당 url로 요청이 들어오면 "hello" 서블릿을 수행
                });
                webServer.start();

            }
        };

        // 자바 구성정보 등록해주기
        applicationContext.register(HellobootApplication.class);
        applicationContext.refresh(); // 스프링 컨테이너가 초기화 될 때 빈들을 만들어줌


    }

}
