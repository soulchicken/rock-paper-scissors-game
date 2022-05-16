package com.game.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	// 사용하고자 하는 스키마(Database) 이름
	private static final String DATABASE_NAME = "testdb";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	
	
	// Java와 MySQL database와 연결하는 메소드
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
		return connection;
	}
}
