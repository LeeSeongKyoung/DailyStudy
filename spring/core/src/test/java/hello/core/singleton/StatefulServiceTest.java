package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		// 빈 조회
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		// ThreadA : A사용자가 10000원 주문
		// statefulService1.order("userA", 10_000);
		// statefulService2.order("userB", 20_000);


		// ** 해결책 : 지역변수 설정
		int userAPrice = statefulService1.order("userA", 10_000);
		// ThreadB : B사용자가 20000원 주문
		int userBPrice = statefulService2.order("userB", 20_000);

		// ThreadA : 사용자A 주문하고 주문 금액 조회 요청을 하는 사이에 B사용자가 주문

//		int price = statefulService1.getPrice();
		System.out.println("price = " + userAPrice); // 출력은 2만원이 나옴, 중간에 사용자B가 금액을 바꿔버렸기 때문
		           								// service1, 2 모두 인스턴스는 같기 때문

		// price는 2만원을 꺼내게 됨(문제점)
//		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20_000);

	}

	static class TestConfig{

		@Bean
		public StatefulService statefulService(){
			return new StatefulService();
		}
	}

}