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

@Configuration // AppConfig에 설정을 구성한다는 뜻
public class AppConfig {
	// 애플리케이션 환경 설정을 여기서 다 함
	// 주입 -> 생성자를 통해서 주입(injection) 
	// 객체의 생성과 연결은 AppConfig가 담당 (관심사 분리)
	// 실제 실행하는 역할 MemberServiceImpl, OrderServiceImpl
	// 클라이언트인 MemberServiceImpl입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI
	// @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록


	// * @Configuration과 싱글톤
	// @Bean memberService -> new MemoryMemberRepository() 호출
	// @Bean orderService -> new MemoryMemberRepository() 호출
	// 2번이나 호출됨(싱글톤이 깨지는 것처럼 보임 -> MemberServiceImpl ㄱㄱ memberRepository값 확인하기)

	// 5개가 출력될 것이라고 예상
	// call AppConfig.memberService
	// call AppConfig.memberRepository
	// call AppConfig.memberRepository
	// call AppConfig.orderService
	// call AppConfig.memberRepository

	// 실제 3개만 출력(스프링은 싱글톤을 보장해줌)
	// call AppConfig.memberService
	// call AppConfig.memberRepository  => 한번만 출력
	// call AppConfig.orderService

	// @Configuration 에 답이 있음(ConfigurationsSingletonTest ㄱㄱ)



	@Bean
	public MemberService memberService(){
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository()); // 생성자 주입
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(
				memberRepository(),
				discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}
	// 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않음


	// appConfig 리팩토링
	@Bean
	public DiscountPolicy discountPolicy(){
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}



}
