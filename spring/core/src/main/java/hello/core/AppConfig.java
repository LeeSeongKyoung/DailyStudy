package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	// 애플리케이션 환경 설정을 여기서 다 함
	// 주입 -> 생성자를 통해서 주입(injection) 
	// 객체의 생성과 연결은 AppConfig가 담당 (관심사 분리)
	// 실제 실행하는 역할 MemberServiceImpl, OrderServiceImpl
	// 클라이언트인 MemberServiceImpl입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI

	@Bean
	public MemberService memberService(){
		return new MemberServiceImpl(memberRepository()); // 생성자 주입
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	// 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않음

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(
				memberRepository(),
				discountPolicy());
	}
	// appConfig 리팩토링

	@Bean
	public DiscountPolicy discountPolicy(){
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}



}
