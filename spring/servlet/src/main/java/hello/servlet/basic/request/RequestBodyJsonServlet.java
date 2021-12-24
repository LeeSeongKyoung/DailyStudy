package hello.servlet.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * http://localhost:8080/request-body-json
 *
 * JSON 형식 전송
 * content-type: application/json
 * message body: {"username": "hello", "age": 20}
 *
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

	// helloDate 변환시키기
	// json 라이브러리 필요
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		System.out.println("messageBody = " + messageBody);

		// 객체로 전환
		HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

		System.out.println("helloData.username = " + helloData.getUsername());
		System.out.println("helloData,age = " + helloData.getAge());

		response.getWriter().write("ok");
	}
}
