package hello.core.discount;

import hello.core.member.Member;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jeong JaeUk
 */
public interface DiscountPolicy {

	int discount(Member member, int price);
}
