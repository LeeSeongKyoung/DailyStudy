package hello.servlet.web.frontController.v4.controller;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
	// ModelView가 아예 필요가 없음
	// 정말 단순하게 new-form이라는 뷰의 논리 이름만 반환
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "new-form";
	}
}
