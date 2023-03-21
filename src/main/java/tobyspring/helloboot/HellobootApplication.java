package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class HellobootApplication {

	public static void main(String[] args) {
		// 1. 서블릿 컨테이너(톰캣) 띄우기
		// -> 스프링부트 제공)내장형 톰캣의 초기화와 간편 설정 지원하는 TomcatServletWebServerFactory를 사용
		// TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // jetty 등 다양한 웹 서버 지원을 위한 추상화된 ServletWebServerFactory 인터페이스
		WebServer webServer = serverFactory.getWebServer();
		webServer.start();

	}

}
