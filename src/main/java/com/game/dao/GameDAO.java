package com.game.dao;

import java.sql.Connection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.game.utils.DBUtils;


public class GameDAO {

	//kim
	public int saveUser(int userId, String userName, String password, int isLogin) {
		final String insertQuery = "INSERT INTO user (user_name, password, is_login) VALUES (?,?,?)";
		int affectedRows = 0;
		try (
				// java와 MySQL의 연결통로 (Connection) 생성
				Connection connection = DBUtils.getConnection();
				// 통로를 통해 SQL을 전달할 객체 Statement 생성
				PreparedStatement preparedStatement = createPrepaeredStatement(connection, insertQuery, userId, userName, password, isLogin);
				// 실제 쿼리 전달 및 수행 (진행 시켜)
		)
		{
			affectedRows = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	private PreparedStatement createPrepaeredStatement(Connection connection, String sql, int userId,
													   String userName, String password, int isLogin) throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, password);
		preparedStatement.setInt(3, isLogin);

		return preparedStatement;
	}

	public boolean checkUserExistence(String sql, String id) {
		boolean result = false;
		try (
				// java와 MySQL의 연결통로 (Connection) 생성
				Connection connection = DBUtils.getConnection();
				// 통로를 통해 SQL을 전달할 객체 Statement 생성
				PreparedStatement preparedStatement = createPrepaeredStatement(connection, sql, id);
				// 실제 쿼리 전달 및 수행 (진행 시켜)
		)
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("user_name") != id) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private PreparedStatement createPrepaeredStatement(Connection connection, String sql, String id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
		return preparedStatement;
	}

	public boolean checkUserId(String name) {
		final String checkIdQuery = "SELECT user_name FROM user WHERE user_name = ?";
		// 아이디가 있으면 true, 없으면 false 반환
		return checkUserExistence(checkIdQuery, name);
	}

	public void saveScore(String userName) {
		int userId = findByName(userName);
		final String insertQuery = "INSERT INTO score (user_id, win, lose, draw) VALUES (?, 0, 0, 0)";
		int affectedRows = 0;
		try (
				// java와 MySQL의 연결통로 (Connection) 생성
				Connection connection = DBUtils.getConnection();
				// 통로를 통해 SQL을 전달할 객체 Statement 생성
				PreparedStatement preparedStatement = createPrepaeredStatement(connection, insertQuery, userId);
				// 실제 쿼리 전달 및 수행 (진행 시켜)
		)
		{
			affectedRows = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PreparedStatement createPrepaeredStatement(Connection connection, String sql, int userId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		return preparedStatement;
	}

	public int findByName(String name) {
		final String selectQuery = "SELECT user_id FROM user WHERE user_name = ? ";
		int id = 0;
		try (
				// java와 MySQL의 연결통로 (Connection) 생성
				Connection connection = DBUtils.getConnection();
				// 통로를 통해 SQL을 전달할 객체 Statement 생성
				PreparedStatement preparedStatement = createPrepaeredStatement(connection, selectQuery, name);
				// 실제 쿼리 전달 및 수행 (진행 시켜)
				ResultSet resultSet = preparedStatement.executeQuery();
		)
		{
			if (resultSet.next()) {
				id = resultSet.getInt("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	private PreparedStatement preparedStatement;

	/**
	 * 생성자
	 */
	public GameDAO() {
		super();
	}



	private PreparedStatement createPreparedStatement(Connection connection, String query, String userId, String password) throws SQLException {
		preparedStatement = connection.prepareStatement(query); // query 전달 객체
		preparedStatement.setString(1, userId);
		preparedStatement.setString(2, password);
		return preparedStatement;
	}

	private PreparedStatement createPreparedStatement(Connection connection, String query, String userName) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, userName);
		return preparedStatement;
	}

	/**
	 * login_log 테이블에 로그를 추가하는 query
	 *
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


	public boolean checkUserExistence(String sql, String id, String password) {
		boolean result = false;
		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = createPreparedStatement(connection, sql, id, password);) {
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

	public int findUserIdNyName(String userName) {
		String findUserIdQuery = "SELECT user_id FROM user WHERE user_name = ?";
		int id = 0;

		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = createPreparedStatement(connection, findUserIdQuery, userName);
			 ResultSet resultSet = preparedStatement.executeQuery();) {
			if (resultSet.next()) {
				id = resultSet.getInt("user_id");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}


	private int addLoginLog(String userName, String now, int loginSwitch) {
		// TODO Auto-generated method stub
		String addLogQuery;

		int updatedRow = 0;

		if (loginSwitch == 1) {
			addLogQuery = String.format("INSERT INTO login_log (user_id, login_time) VALUES (?, \"%s\")", now);
		} else {
			addLogQuery = String.format("UPDATE login_log SET logout_time = \"%s\" WHERE user_id = ? and logout_time IS NULL", now);
		}

		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = createPreparedStatement(connection, addLogQuery, findUserIdNyName(userName));) {
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return updatedRow;

	}


	public int setLoginLogout(String userName, String password, int loginSwitch) {
		String userCheckQuery = "SELECT * FROM user WHERE user_name = ? AND password = ?";
		if (checkUserExistence(userCheckQuery, userName, password)) {

			LocalDateTime now = LocalDateTime.now();
			String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			String loginLogoutQuery = String.format("UPDATE user SET is_login = %d WHERE user_name = ? AND password = ?", loginSwitch);

			try (Connection connection = DBUtils.getConnection();
				 PreparedStatement preparedStatement = createPreparedStatement(connection, loginLogoutQuery, userName, password);) {
				addLoginLog(userName, formattedNow, loginSwitch);
				return preparedStatement.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return 0;
	}



	public int login(String userName, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userName, password, 1);
	}


	public int logout(String userName, String password) {
		// TODO Auto-generated method stub
		return setLoginLogout(userName, password, 0);
	}
}