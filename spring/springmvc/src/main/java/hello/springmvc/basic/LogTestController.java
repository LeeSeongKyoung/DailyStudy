package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // HTTP 메시지 바디에 바로 입력
public class LogTestController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping("/log-test")
	public String logTest() {
		String name = "Spring";

		System.out.println("name = " + name);

		// LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
		/*개발 서버는 debug 출력
		운영 서버는 info 출력*/
		log.trace("trace log={}", name);
		log.debug(" debug log={}", name);
		log.info(" info log={}", name);
		log.warn(" warn log={}", name);
		log.error(" error log={}", name);

		return "ok";
	}

}
