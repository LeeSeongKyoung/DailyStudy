package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		basePackages = "hello.core.member", // member만 componentScan의 대상이 된다
		// ** 지정하지 않으면?
		// 찾는데 시간이 오래걸릴 수 있음 p89

		// @ComponentScan가 붙은 설정 정보 클래스의 패키지가 시작위치
		// hello.core
		// Conponent 애노테이션이 붙은 클래스를 자동으로 빈 등록

		/*
		컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
		AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다.
		 (수동 등록도 포함되어버림)
		 그래서 excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트
		스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다
		*/
		// 뺄 것들
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class )
)

public class AutoAppConfig {
}
