package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String ... args) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                //dispatcherServlet.setApplicationContext(this);
                // -> dispatcher는 ServletApplicationContextAware라는 스프링 컨테이너를 setter 메소드로 주입해주는 메소드를 가진 인터페이스를 구현해놨고,
                // -> 이런 생애주기 빈 메소드를 가진 빈이 등록되면 스프링은 자신을 직접 주입해준다.
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    // 매핑
                    servletContext.addServlet("dispatcherServlet",
                        dispatcherServlet
                    ).addMapping("/*");
                });
                webServer.start();

            }
        };

        // 자바 구성정보 등록해주기
        applicationContext.register(applicationClass);
        applicationContext.refresh(); // 스프링 컨테이너가 초기화 될 때 빈들을 만들어줌
    }
}
