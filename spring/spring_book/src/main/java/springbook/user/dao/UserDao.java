package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

//public abstract class UserDao {  p63 abstract
public class UserDao {

//	private SimpleConnectionMaker simpleConnectionMaker; // 아예 클래스 구분
	private ConnectionMaker connectionMaker; // 인터페이스를 통해 오브젝트에 접근 -> 구체적인 클래스 정보 알 필요없음

//	public UserDao(){
	public UserDao(ConnectionMaker connectionMaker){ // 클라이언트가 미리 만들어둔 ConnectionMaker의 오브젝트를 전달 받을 수 있도록 파라미터 하나 추가
//		simpleConnectionMaker = new SimpleConnectionMaker();
//		connectionMaker = new DConnectionMaker();
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
//		Connection c = simpleConnectionMaker.makeNewConnection();
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");

		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
//		Connection c = getConnection();
//		Connection c = simpleConnectionMaker.makeNewConnection();
		Connection c = connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1,id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		rs.close();
		ps.close();
		c.close();

		return user;
	}

//	private abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//	{
//		Class.forName("org.h2.Driver");
//		Connection c = DriverManager.getConnection(
//				"jdbc:h2:tcp://localhost/~/test", "user", "password");
//		return c;
//	}


}
