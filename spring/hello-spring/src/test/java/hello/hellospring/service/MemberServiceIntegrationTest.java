package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void join() {
		// given : 무언가가 주어졌는데 (기반)
		Member member = new Member();
		member.setName("spring2");

		// when : 이것을 실행했을때 (검증)
		Long saveId = memberService.join(member);
		// then : 결과
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName()); // Assertj로 import
	}

	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		// when
		memberService.join(member1);

		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

	}

}