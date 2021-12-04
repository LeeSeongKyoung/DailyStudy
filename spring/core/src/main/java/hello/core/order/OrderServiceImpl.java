package hello.core.order;


import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  // 빈으로 등록
@MainDiscountPolicy
//@RequiredArgsConstructor // 롬복 : 생성자 자동으로 만듦 -> 기존 생성자 삭제하기
public class OrderServiceImpl implements OrderService{

	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	// 생성자 set 셋다 Autowired 있으면 생성자 먼저
	// @Autowired(required = false) -> 주입할 대상이 없어도 동작하게 하려면
/*

	@Autowired
	public void setMemberRepository(MemberRepository memberRepository){
		System.out.println("memberRepository = " + memberRepository);
		this.memberRepository = memberRepository;
	}
	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy){
		System.out.println("discountPolicy = " + discountPolicy);
		this.discountPolicy = discountPolicy;
	}
*/
	@Autowired // @Componet 등록하면 @Autowired 생성자에 붙여주면 스프링이 의존관계 주입을 자동으로 해줌
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// * 싱글톤 문제 (테스트 용도)
	public MemberRepository getMemberRepository(){
		return memberRepository;
	}
}
