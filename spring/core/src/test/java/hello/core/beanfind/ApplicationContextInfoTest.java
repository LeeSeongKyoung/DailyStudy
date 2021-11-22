package hello.core.beanfind;


import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean(){
		// 빈 정의된 이름을 다 등록
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			Object bean = ac.getBean(beanDefinitionName); // 타입을 모르기 때문에 object가 꺼내짐
			// key : beanDefinitionNam / value : bean
			System.out.println("name = " + beanDefinitionName + "object = " + bean );
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean(){
		// 빈 정의된 이름을 다 등록
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);  // 스프링 내부에 등록된 빈들이 아니라 외부 라이브러리 등 개인이 개발을 위해 등록한 빈
			// beanDefinition : 빈 하나 하나에 대한 정보들

			//Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
			//Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("name = " +  beanDefinitionName + "object = " + bean );
			}
		}
	}
}
