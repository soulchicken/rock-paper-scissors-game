package com.game.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * DB와 관련된 설정 정보나 수행들을 관리하는 클래스
 * @author uyggnodkrap
 *
 */
public class DBUtils {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/"; // mysql server url
	private static final String DATABASE_NAME = "rsptest"; // database schema name
	private static final String USER = "root"; 
	private static final String  PASSWORD = "1234";

	//	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	//	JDBC4.0 이후로 모든 드라이버들은 클래프새스에 자동으로 로딩됨. (기존에는 원칙적으로 드라이버를 먼저 로딩했어야 했음)


	/**
	 * java application에서 DB까지의 connection 생성
	 * 
	 * Connection 클래스
	 * A connection (session) with a specific database. 
	 * SQL statements are executed and results are returned within the context of a connection. 
	 * 
	 * DriverManager.getConnection(): return Connection instance
	 * Attempts to establish a connection to the given database URL.
	 * The DriverManager attempts to select an appropriate driver from the set of registered JDBC drivers.
	 * Parameters: url a database url of the form  jdbc:subprotocol:subnameReturns:a connection to the URL
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
		return connection;

	}

	/**
	 * DDL
	 * 테이블 생성 메서드 
	 * 
	 * 같은 테이블 두 번 만드면 에러나니까 drop하고 create 하는 메서드
	 * 
	 * try - with resource: 자원을 자동으로 해제
	 */
	public static void dropAndCreateTable() {
		try ( Connection connection = getConnection();
				Statement statement = connection.createStatement();	) {			
			//	테이블 제거 query: DROP TABLE IF EXIST
			final String dropUserTableQueryIfExist = "DROP TABLE IF EXISTS user";


			

			//	테이블 생성 query
			final String ceateUserTableQuery = 
					"CREATE TABLE user (" +
							"user_id SMALLINT(5) unsigned NOT NULL AUTO_INCREMENT," +
							"user_name VARCHAR(45) NOT NULL," +
							"password VARCHAR(45) NOT NULL," +
							"is_login BOOLEAN DEFAULT false," +
							"PRIMARY KEY (id))"+
							"CONSTRAINT pk_person PRIMARY KEY (user_id)";
			
	
							
							
			statement.execute(ceateUserTableQuery); // query 실행 요청 날리기 (return 성공유무 (boolean))
			
			System.out.println("done!"); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
