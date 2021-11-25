package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {  
	// UserDao를 추상화해서 진행한 클래스
/*
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		 //N사 DB Connection 생성코드
		Class.forName("org.h2.Driver");
		Connection c = DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/test", "user", "password");
		return c;
	}
*/

}
