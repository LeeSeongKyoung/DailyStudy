package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test", "user", "password");
		return c;
//		return null; // D사의 독자적인 방법으로 Connection을 생성하는 코드
	}
}
