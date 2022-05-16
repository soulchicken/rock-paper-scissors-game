package com.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.game.model.User;
import com.game.utils.DBUtils;

public class GameDAO {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;

	
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
}
