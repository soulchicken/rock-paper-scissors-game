package com.game.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtils {
 	private static final String DB_URL = "jdbc:mysql://localhost:3306/"; // mysql server url
 	private static final String DATABASE_NAME = "rsptest"; // database schema name
 	private static final String USER = "root";
 	private static final String  PASSWORD = "1234";

 	public static Connection getConnection() throws SQLException{
 		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
 		return connection;

 	}

	public static void dropAndCreateTable() {
		try ( Connection connection = getConnection();
				Statement statement = connection.createStatement();	) {			
			//	테이블 제거 query: DROP TABLE IF EXIST
			final String dropUserTableQueryIfExist = "DROP TABLE IF EXISTS user";
			statement.execute(dropUserTableQueryIfExist); // query 실행 요청 날리기 (return 성공유무 (boolean))
			final String dropLoginLogTableQueryIfExist = "DROP TABLE IF EXISTS login_log";
			statement.execute(dropLoginLogTableQueryIfExist); // query 실행 요청 날리기 (return 성공유무 (boolean))

			//	테이블 생성 query
			final String createUserTableQuery = 
					"CREATE TABLE user (" +
							"user_id SMALLINT(5) unsigned NOT NULL AUTO_INCREMENT," +
							"user_name VARCHAR(45) NOT NULL," +
							"password VARCHAR(45) NOT NULL," +
							"is_login BOOLEAN DEFAULT false," +
							"CONSTRAINT pk_user PRIMARY KEY (user_id)";
			
			statement.execute(createUserTableQuery); // query 실행 요청 날리기 (return 성공유무 (boolean))				
			final String createdropLoginLogTableQuery = "CREATE TABLE login_log (" + 
													"log_id SMALLINT(5) unsigned NOT NULL AUTO_INCREMENT," + 
													"user_id SMALLINT(5) unsigned NOT NULL," + 
													"login_time DATETIME," + 
													"logout_time DATETIME," + 
													"CONSTRAINT pk_log PRIMARY KEY (log_id)," + 
													"FOREIGN KEY (user_id) REFERENCES user(user_id))";
			
			statement.execute(createdropLoginLogTableQuery); // query 실행 요청 날리기 (return 성공유무 (boolean))
			System.out.println("table created"); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}