package springbook.user.dao;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// UserDao의 생성 책임을 맡은 팩토리 클래스
@Configuration  // -> 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정 정보라는 표시
public class DaoFactoty {
	/*public UserDao userDao(){  -> DaoFactoty에서 생성 역할 분리
		ConnectionMaker connectionMaker = new DConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);

		return userDao;
	}*/

	// 만약 dao가 여러개라면? --> ConnectionMaker 구현 클래스를 선정하고 생성하는 코드의 중복(분리해서 해결)
	@Bean // -> 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
	public UserDao userDao(){
//		return new UserDao(new DConnectionMaker());
		return new UserDao(connectionMaker());
	}



	// 다양한 dao 예시
/*	public AccountDao accountDao(){
		return new AccountDao(connectionMaker());
	}

	public  MessageDao messageDao(){
		return new MessageDao(connectionMaker());
	}
	*/
	// 생성 오브젝트 코드 수정
	// 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성코드
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
}
