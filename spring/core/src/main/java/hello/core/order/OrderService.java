package hello.core.order;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jeong JaeUk
 */
public interface OrderService {
	Order createOrder(Long memberId, String itemName, int itemPrice);
}
