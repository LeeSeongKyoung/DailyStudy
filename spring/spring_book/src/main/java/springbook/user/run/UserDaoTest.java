package springbook.user.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.*;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

//		ConnectionMaker connectionMaker = new DConnectionMaker();   -> 제어의 역전 p88 DaoFactory로 역할 분리
		// UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트를 만듦
//		UserDao dao = new UserDao(connectionMaker);
		// 1. UserDao 생성 2. 사용할 ConnectionMaker 타입의 오브젝트 제공. 결국 두 오브젝트 사이의 의존관계 설정효과

		// ApplicationContext의 getBean이라는 메소드를 이용해 UserDao의 오브젝트를 가져올 수 있음 p97
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);


		// 제어의 역전 p88 DaoFactory로 역할 분리
//		UserDao dao = new DaoFactoty().userDao();

		// spring bean 적용 p97
		UserDao dao = context.getBean("userDao", UserDao.class);

/*
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		dao.add(user);

		System.out.println(user.getId() + " 등록 성공");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());

		System.out.println(user2.getId() + " 조회 성공");
*/

		// DaoFactory를 여러번 호출했을 때 새로운 오브젝트가 만들어져서 돌아옴
		DaoFactory factory = new DaoFactory();

		UserDao dao1 = factory.userDao();
		UserDao dao2 = factory.userDao();

		// 다른 값
		System.out.println("dao1 = " + dao1);
		System.out.println("dao2 = " + dao2);

		UserDao dao3 = context.getBean("userDao", UserDao.class);
		UserDao dao4= context.getBean("userDao", UserDao.class);

		// 같은 값
		System.out.println(dao3);
		System.out.println(dao4);

	}
}
