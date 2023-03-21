package tobyspring.helloboot;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HellobootApplication {

    public static void main(String[] args) {
        // 1. 서블릿 컨테이너(톰캣) 띄우기
        // -> 스프링부트 제공)내장형 톰캣의 초기화와 간편 설정 지원하는 TomcatServletWebServerFactory를 사용
        // TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // => jetty 등 다양한 웹 서버 지원을 위한 추상화된 ServletWebServerFactory 인터페이스 사용

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                    // Request > 쿼리스트링 파라미터로 받은 값을 출력하는 기능을 요청
                    // -> getParameter로 값 가져오기
                    String name = req.getParameter("name");

                    // Response > 웹 응답 3가지 요소 갖춰서 응답 반환
                    resp.setStatus(HttpStatus.OK.value()); // Status Line
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // Headers
                    resp.getWriter().println("Hello " + name); // Message Body
                    super.service(req, resp);
                }
            }).addMapping("/hello"); // 매핑되는 url -> 해당 url로 요청이 들어오면 "hello" 서블릿을 수행
        });
        webServer.start();

    }

}
