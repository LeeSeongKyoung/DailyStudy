package hello.core.beanfind;


import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	void findBeanByName(){
		MemberService memberService = ac.getBean("memberService", MemberService.class);
//		System.out.println("memberService = " + memberService);
//		System.out.println("memberService.getClass = " + memberService.getClass());
		 // MemberService interface로 조회함
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}
	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	void findBeanByType(){
		MemberService memberService = ac.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체 타입으로 조회")
	void findBeanByName2(){
		// MemberServiceImpl로 조회
		// 구체 타입으로 조회하는 것은 좋지 않음(구현에 의존하는 것은 좋지 않음)
		MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("빈 이름으로 조회x")
	void findBeanByNameX(){
		// ac.getBean("xxxxx", MemberService.class); -> 실제로 존재하지 않은 빈
//		MemberService xxxxxx = ac.getBean("xxxxxx", MemberService.class);
		// NoSuchBeanDefinitionException: No bean named 'xxxxx' available 오류
		assertThrows(NoSuchBeanDefinitionException.class,
				() -> ac.getBean("xxxxx", MemberService.class));
		// 오른쪽을 실행하면 왼족 예외가 터져야 함
	}


}
