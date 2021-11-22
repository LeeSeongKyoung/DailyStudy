package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

	@Test
	void xmlAppContext(){
		ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
		MemberService memberService = ac.getBean("memberService", MemberService.class); // 빈 조회
		assertThat(memberService).isInstanceOf(MemberService.class);
	}
}
