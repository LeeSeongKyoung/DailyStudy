package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

// CountingConnectionMaker 의존관계가 추가된 DI 설정용 클래스
@Configuration
public class CountingDaoFactory {

/*
	p127 수정자 메소드 DI 방식을 사용한 UserDao -> userDao 생성자 코드 삭제 후 error를 위해 잠시 주석 처리
	@Bean
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}*/

	@Bean
	public ConnectionMaker connectionMaker(){
		return new CountingConnectionMaker(realConnectionMaker());
	}

	@Bean
	public ConnectionMaker realConnectionMaker(){
		return new DConnectionMaker();
	}

}
