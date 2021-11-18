package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

	MemberService memberService;
	// clear
	// MemberService에서도 new MemoryMemberRepository()로 같은 이름?을 쓰기 때문에 코드 변경해주는 것이 좋음
	// MemberService에서 처리
	MemoryMemberRepository memberRepository;

	@BeforeEach
	public void beforeEach(){
		// test 실행 전에 memoryMemberRepository 만듦 -> 만든 것을 위의 memberRepository에 넣어놓고 memberService에 넣어둠
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	// test를 하나 끝나고 나면 test를 clear 시켜줘야함
	@AfterEach
	public void afterEach(){
		memberRepository.clearStore();
	}


	@Test
	void join() {
		// given : 무언가가 주어졌는데 (기반)
		Member member = new Member();
		member.setName("spring");

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
		// 두번째 방법) assertThrows (람다)
//		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));

/*
		첫번째 방법) try~catch
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