package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

	// 회원서비스를 만드려면 회원 repository가 필요
	private final MemberRepository memberRepository;

	// 외부에서 넣을 수 있도록(DI)
	// constructor 생성
//	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입
	public Long join(Member member){
		// 같은 이름이 있는 중복 회원x

//		Optional<Member> result = memberRepository.findByName(member.getName());
//		// Optional 함수
//		result.ifPresent(m -> {
//			throw new IllegalStateException("이미 존재하는 회원입니다.");
//		});

		// 어차피 result가 반환되었기 때문에 ifPresent가 바로 들어갈 수 있음
		// ctrl +alt + shift + t : 리팩토링 관련 전체 항목 조회 → extract Method 클릭
		// 하단의 validateDuplicateMember(Member member) method가 생성됨
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
