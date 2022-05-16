package com.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.game.utils.DBUtils;



public class Dao {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public int save(int user_id, int winScore, int loseScore, int drawScore) {
		String insertQuery = "UPDATE score SET win = ?, lose = ?, draw = ? WHERE user_id = ?";
		int affectedRows = 0;
		
		try (
				Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, insertQuery, winScore, loseScore, drawScore, user_id);
			)
		{
			affectedRows = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String sql, int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		return preparedStatement;
	}
	
	private PreparedStatement createPreparedStatement(Connection connection, String sql, int win,int lose, int draw, int user_id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setInt(1, win);
		preparedStatement.setInt(2, lose);
		preparedStatement.setInt(3, draw);
		preparedStatement.setInt(4, user_id);
		
		return preparedStatement;
	}

	public int[] loadScore(int id) {
		String loadQuery = "SELECT * FROM score WHERE user_id = ?";
		int[] scores = new int[3];
		
		try (
				Connection connection = DBUtils.getConnection();
				PreparedStatement preparedStatement = createPreparedStatement(connection, loadQuery, id);
			)
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				scores[0] = resultSet.getInt("win");
				scores[1] = resultSet.getInt("lose");
				scores[2] = resultSet.getInt("draw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scores;
	}
}
