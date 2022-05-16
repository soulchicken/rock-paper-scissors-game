package com.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.game.utils.DBUtils;


public class GameDAO {

	private PreparedStatement preparedStatement;

	/**
	 * 생성자
	 */
	public GameDAO() {
		super();
	}

	/**
	 * 로그인 할 때 Id, password가 일치하는지에 대한 query 
	 * @param connection
	 * @param sql
	 * @param userId
	 * @param password
	 * @return DML (INSERT)
	 * @throws SQLException
	 */
	private PreparedStatement createPreparedStatement(Connection connection, String query, String userId, String password) throws SQLException {
		preparedStatement = connection.prepareStatement(query); // query 전달 객체
		preparedStatement.setString(1, userId);  
		preparedStatement.setString(2, password); 
		return preparedStatement; 
	}

	/**
	 * user_name 가지고 user_id 조회하는 query
	 * @param connection
	 * @param query
	 * @param userId
	 * @return DML (SELECT)
	 * @throws SQLException
	 */
	private PreparedStatement createPreparedStatement(Connection connection, String query, String userName) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, userName); 
		return preparedStatement;
	}

	/**
	 * login_log 테이블에 로그를 추가하는 query
	 * @param connection
	 * @param query
	 * @param userId
	 * @return DML (SELECT)
	 * @throws SQLException
	 */
	private PreparedStatement createPreparedStatement(Connection connection, String query, int userId) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, userId);
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
		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = createPreparedStatement(connection, sql, id, password);)
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = true;

			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * user_id 찾는 메서드
	 * @param userName
	 * @return user_id
	 */
	private int findUserIdNyName (String userName) {
		String findUserIdQuery = "SELECT user_id FROM user WHERE user_name = ?";
		int id = 0;
		
		try (Connection connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = createPreparedStatement(connection, findUserIdQuery, userName);
			ResultSet resultSet = preparedStatement.executeQuery();)
		{
			if (resultSet.next()) {
			id = resultSet.getInt("user_id");
		}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}


	/**
	 * login logout 추가 메서드
	 * @param userId
	 * @param now
	 * @param loginSwitch 0 -> logout, 1 -> login
	 */
	private int addLoginLog(String userName, String now, int loginSwitch) {
		// TODO Auto-generated method stub
		String addLogQuery;

		int updatedRow = 0;

		if (loginSwitch == 1) { addLogQuery = String.format("INSERT INTO login_log (user_id, login_time) VALUES (?, \"%s\")", now); }
		else { addLogQuery = String.format("UPDATE login_log SET logout_time = \"%s\" WHERE user_id = ? and logout_time IS NULL", now); }

		try (Connection connection = DBUtils.getConnection();
			PreparedStatement preparedStatement = createPreparedStatement(connection, addLogQuery, findUserIdNyName(userName));)
		{
			return preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return updatedRow;

	}

	/**
	 * loginSwitch 값에 따라 로그인 또는 로그아웃을 하는 메서드 
	 * @param userId
	 * @param password
	 * @param loginSwitch 1이면 로그인, 0이면 로그아웃
	 * @return 변경된 row 개수
	 */
	public int setLoginLogout(String userName, String password, int loginSwitch) {
		String userCheckQuery = "SELECT * FROM user WHERE user_name = ? AND password = ?";
		if (checkUserExistence(userCheckQuery, userName, password)) {

			LocalDateTime now = LocalDateTime.now();
			String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			String loginLogoutQuery = String.format("UPDATE user SET is_login = %d WHERE user_name = ? AND password = ?", loginSwitch);

			try (Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, loginLogoutQuery, userName, password);)
			{
				addLoginLog(userName, formattedNow, loginSwitch);
				return preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
	public int login(String userName, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userName, password, 1);
	}

	/**
	 * 로그아웃메서드
	 * @param userId
	 * @param password
	 * @return
	 */
	public int logout(String userName, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userName, password, 0);
	}
}
