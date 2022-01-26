package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v4.ControllerV4;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

	// 메인 코드는 건드리지 않고 이 메소드만 추가해서 ControlleV4를 사용함
	// OCP 원칙을 잘 지킴
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4); // ControllerV4 버전을 지원하는지 체크
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handler;

		Map<String, String> paramMap = createparamMap(request);
		HashMap<String, Object> model = new HashMap<>();

		String viewName = controller.process(paramMap, model);

		// 이 어댑터에서 ModelView 생성, Model 세팅
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);

		return mv;
	}

	private Map<String, String> createparamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}

}
