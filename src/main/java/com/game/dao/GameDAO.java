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
	 * loginSwitch 값에 따라 로그인 또는 로그아웃을 하는 메서드 
	 * @param userId
	 * @param password
	 * @param loginSwitch 1이면 로그인, 0이면 로그아웃
	 * @return 변경된 row 개수
	 */
	public int setLoginLogout(String userId, String password, int loginSwitch) {
		String userCheckQuery = "SELECT * FROM user WHERE user_name = ? AND password = ?";
		if (checkUserExistence(userCheckQuery, userId, password)) {
			String loginLogoutQuery = String.format("UPDATE user SET is_login = %d WHERE user_name = ? AND password = ?", loginSwitch);
			
			try (
					Connection connection = DBUtils.getConnection();
					PreparedStatement preparedStatement = createPreparedStatement(connection, loginLogoutQuery, userId, password);

					){
				System.out.println("Service -> DAO");
				return preparedStatement.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		return 0;
	}
	
	/**
	 * 로그인 메서드
	 * @param userId
	 * @param password
	 * @return
	 */
	public int login(String userId, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userId, password, 1);
	}

	/**
	 * 로그아웃메서드
	 * @param userId
	 * @param password
	 * @return
	 */
	public int logout(String userId, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userId, password, 0);
	}
	
	

}
