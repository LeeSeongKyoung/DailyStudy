package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
//@Repository
public class MemoryMemberRepository implements MemberRepository{

	private static Map<Long,Member> store = new HashMap<>();
	private static long sequence = 0L;


	@Override
	public Member save(Member member) {
		// member를 save할때 먼저 sequence 하나를 올려움
		// 1. 아이디 셋팅
		member.setId(++sequence);
		// 2. store 저장(Map에 저장)
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// null 값이 나와도 Optional로 감싸서 처리
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// 람다 사용(java)
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny(); // 하나라도 찾기
	}

	@Override
	public List<Member> findAll() {
		// store의 member들
		return new ArrayList<>(store.values());
	}
	
	// store를 싹 비움 -> test할 때 사용
	public void clearStore(){
		store.clear();
	}
}
