package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SeongKyoung
 */

@Controller
@RequiredArgsConstructor
public class LogDemoController {

	// 요청이 많이 들어와도 요청을 각각 따로 관리하는 것이 핵심
	//

	private final LogDemoService logDemoService;
	private final MyLogger myLogger;

	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();


//		System.out.println(" myLogger = " + MyLogger.getClass());
//		myLogger;

		myLogger.log("controller test");
//		Thread.sleep(1000);
		logDemoService.logic("testId");
		return "OK";
	}
}
