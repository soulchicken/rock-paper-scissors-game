package com.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.game.model.Game;
import com.game.utils.DBUtils;


public class GameDAO {
	

	private PreparedStatement preparedStatement;
//	private Game game
	
	/**
	 * 생성자
	 */
	public GameDAO() {
		super();
	}
	
	
	
	
	private PreparedStatement createPreparedStatement(Connection connection, String sql, String userId, String password) throws SQLException {
		preparedStatement = connection.prepareStatement(sql); // query 전달 객체
		preparedStatement.setString(1, userId);  
		preparedStatement.setString(2, password); 
		return preparedStatement; 
	}
	
	/**
	 * 아이디, 비밀번호가 일치하는지 판단하는 메서드 
	 * @param sql
	 * @param id
	 * @param password
	 * @return: 아이디랑 비밀번호가 모두 일치하는 row가 있으면 true
	 */
	public boolean checkUserExistence(String sql, String id, String password) {
		boolean result = false;
		try (
				// java와 MySQL의 연결통로 (Connection) 생성
				Connection connection = DBUtils.getConnection();
				// 통로를 통해 SQL을 전달할 객체 Statement 생성
				PreparedStatement preparedStatement = createPreparedStatement(connection, sql, id, password);
				// 실제 쿼리 전달 및 수행 (진행 시켜)
				ResultSet resultSet = preparedStatement.executeQuery();
			)
		{
			result = preparedStatement.execute();
			if (result) {
				// 아이디가 있어요!

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 로그인 메서드
	 * 로그인 하면 is_login값을 true로 변경하는 메서드
	 * @param userId
	 * @param password
	 * @return
	 */
	public int login(String userId, String password) {
		// TODO Auto-generated method stub
		String userCheckQuery = "SELECT * FROM user WHERE user_name = ? AND password = ?";
		
		
		if (checkUserExistence(userCheckQuery, userId, password)) {
			System.out.println("login check");
	        String loginSettingQuery = String.format("UPDATE user SET is_login = %s WHERE user_name = ? AND password = ?", "1");

			try (
					Connection connection = DBUtils.getConnection();
					PreparedStatement preparedStatement = createPreparedStatement(connection, loginSettingQuery, userId, password);

					
					){
				int a = preparedStatement.executeUpdate();
				if (a != 0) System.out.println("Service -> DAO" + a + "!");
				return a;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		return 0;
	}




	public int logout(String userId, String password) {
		// TODO Auto-generated method stub
		String userCheckQuery = "SELECT * FROM user WHERE user_name = ? AND password = ?";
		
		if (checkUserExistence(userCheckQuery, userId, password)) {
			System.out.println("login check");
	        String loginSettingQuery = String.format("UPDATE user SET is_login = %s WHERE user_name = ? AND password = ?", "0");

			try (
					Connection connection = DBUtils.getConnection();
					PreparedStatement preparedStatement = createPreparedStatement(connection, loginSettingQuery, userId, password);

					
					){
				int a = preparedStatement.executeUpdate();
				if (a != 0) System.out.println("Service -> DAO" + a + "!");
				return a;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
		return 0;
	}
	
	
	
	
	

}
