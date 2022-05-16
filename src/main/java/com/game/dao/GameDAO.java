package com.game.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.game.model.Game;
import com.game.utils.DBUtils;


public class GameDAO {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private Game game;

	private List<Game> games = new ArrayList<>();
	public List<Game> showRank() {
		final String selectQuery = "SELECT * FROM score";
		try {
			connection = DBUtils.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				int user_id = resultSet.getInt("user_id");
				int win = resultSet.getInt("win");
				int lose = resultSet.getInt("lose");
				int draw = resultSet.getInt("draw");
				
				game = new Game(user_id, win, lose, draw);
				games.add(game);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return games;
	}

}
