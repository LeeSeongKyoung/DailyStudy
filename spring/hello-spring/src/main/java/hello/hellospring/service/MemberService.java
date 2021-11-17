package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

	// 회원서비스를 만드려면 회원 repository가 필요
	private final MemberRepository memberRepository = new MemoryMemberRepository();

	// 회원가입
	public Long join(Member member){
		// 같은 이름이 있는 중복 회원x

//		Optional<Member> result = memberRepository.findByName(member.getName());
//		// Optional 함수
//		result.ifPresent(m -> {
//			throw new IllegalStateException("이미 존재하는 회원입니다.");
//		});

		// 어차피 result가 반환되었기 때문에 ifPresent가 바로 들어갈 수 있음
		validateDuplicateMember(member); // 중복회원검증

		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}

	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
