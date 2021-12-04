package hello.core.scan.filter;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

	@Test
	void filterScan(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
//		includeFilters 에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
		BeanA beanA = ac.getBean("beanA", BeanA.class);
		assertThat(beanA).isNotNull();

//		excludeFilters 에 MyExcludeComponent 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
		assertThrows(
				NoSuchBeanDefinitionException.class,
				() -> ac.getBean("beanB", BeanB.class));
	}

	// @Component면 충분하기 때문에, includeFilters 를 사용할 일은 거의 없다.

	@Configuration
	@ComponentScan(
			// FilterType.ANNOTATION : 애노테이션 filterType을 만듦
			includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
			excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
	)
	static class ComponentFilterAppConfig{
	}
}
