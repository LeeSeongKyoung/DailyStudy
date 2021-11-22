package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;

	@Autowired // @Componet 등록하면 @Autowired 생성자에 붙여주면 스프링이 의존관계 주입을 자동으로 해줌
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {

		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {

		return memberRepository.findById(memberId);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository(){
		return memberRepository;
	}
}
