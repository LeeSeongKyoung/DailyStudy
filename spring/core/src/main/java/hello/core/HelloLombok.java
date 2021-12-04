package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SeongKyoung
 */

@Getter
@Setter
@ToString
public class HelloLombok {

	private String name;
	private int age;

	public static void main(String[] args){
		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setName("ddfs");

		String name = helloLombok.getName();
		System.out.println("name = " + name);
	}

}
