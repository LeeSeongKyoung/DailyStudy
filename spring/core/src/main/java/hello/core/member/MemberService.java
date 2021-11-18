package hello.core.member;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jeong JaeUk
 */
public interface MemberService {

	void join(Member member);

	Member findMember(Long memberId);
}
