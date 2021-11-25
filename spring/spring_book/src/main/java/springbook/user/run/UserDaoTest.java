package springbook.user.run;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.DConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionMaker connectionMaker = new DConnectionMaker();
		// UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트를 만듦
		UserDao dao = new UserDao(connectionMaker);
		// 1. UserDao 생성 2. 사용할 ConnectionMaker 타입의 오브젝트 제공. 결국 두 오브젝트 사이의 의존관계 설정효과

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

	}
}
