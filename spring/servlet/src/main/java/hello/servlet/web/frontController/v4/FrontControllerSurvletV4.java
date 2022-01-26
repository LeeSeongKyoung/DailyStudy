package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet( name="frontControllerSurvletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerSurvletV4 extends HttpServlet {
	// 기존 구조에서 모델을 파라미터로 넘기고, 뷰의 논리이름을 반환
	private Map<String, ControllerV4> controllerV1Map = new HashMap<>();

	public FrontControllerSurvletV4() {
		controllerV1Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerV1Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerV1Map.put("/front-controller/v4/members", new MemberListControllerV4());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();

		ControllerV4 controller = controllerV1Map.get(requestURI);
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		Map<String, String> paramMap = createparamMap(request);
		Map<String, Object> model = new HashMap<>(); // 추가
		// 모델 객체를 프론트 컨트롤러에서 생성해서 넘겨줌
		// 컨트롤러에서 모델 객체에 값을 담으면 여기에 그대로 담겨있게 됨

		String viewName = controller.process(paramMap, model);
		MyView view = viewResolver(viewName);
		// 컨트롤러가 직접 뷰의 논리 이름을 반환하므로 이 값을 사용해서 실제 물리 뷰를 찾을 수 있음
		view.render(model, request, response);
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}

	private Map<String, String> createparamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
}
