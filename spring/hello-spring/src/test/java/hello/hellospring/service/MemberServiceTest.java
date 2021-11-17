package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

	MemberService memberService = new MemberService();

	@Test
	void join() {
		// given : 무언가가 주어졌는데 (기반)
		Member member = new Member();
		member.setName("hello");

		// when : 이것을 실행했을때 (검증)
		Long saveId = memberService.join(member);
		// then : 결과
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName()); // Assertj로 import
	}

	@Test
	public void 중복_회원_예외(){
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		// when
//		memberService.join(member1);
//		memberService.join(member2);  // member1, member2 입력값이 같아서 중복회원이 될 수 o
		// 예외처리하기
		memberService.join(member1);
		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
/*

		try{
			memberService.join(member2);
			fail();
		}catch (IllegalStateException e){
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
		}
*/

		// then
	}




	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}