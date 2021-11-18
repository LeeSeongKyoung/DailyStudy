package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	// memberService와 memberRepository를 Bean 직접 등록
	// Controller는 Autowired로
	@Bean
	public MemberService memberService(){
		return new MemberService(memberRepository());  // memberRepository 연결
	}

	@Bean
	public MemberRepository memberRepository(){
		return new MemoryMemberRepository();
	}
}
