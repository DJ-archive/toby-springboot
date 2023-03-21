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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HellobootApplication {

    public static void main(String[] args) {

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller", new HttpServlet() {

                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 인증, 보안, 다국어, 등 공통 기능 수행
                    // 이후 요청 url에 대한 핸들러 매핑
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        // Request
                        String name = req.getParameter("name");
                        // Response
                        resp.setStatus(HttpStatus.OK.value()); // Status Line
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // Headers
                        resp.getWriter().println("Hello " + name); // Message Body
                        super.service(req, resp);

                    } else if (req.getRequestURI().equals("/user")) {
                        System.out.println("user 기능 수행");
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*"); // 매핑되는 url -> 해당 url로 요청이 들어오면 "hello" 서블릿을 수행
        });
        webServer.start();

    }

}
