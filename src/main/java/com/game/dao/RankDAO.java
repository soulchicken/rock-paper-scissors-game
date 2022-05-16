package com.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.game.model.Game;
import com.game.utils.DBUtils;
import com.game.utils.hwangDBUtils;


public class RankDAO {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Game game;
    private List<Game> games = new ArrayList<>();
    private List<Game> calGames = new ArrayList<>();
    private List<Game> oddsRate = new ArrayList<>();
    private float calOdds;
    private PreparedStatement preparedStatement;

    public List<Game> showRank() {
        final String selectQuery = "SELECT * FROM score";
        try {
            connection = hwangDBUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                int win = resultSet.getInt("win");
                int lose = resultSet.getInt("lose");
                int draw = resultSet.getInt("draw");
                int totalgames = win + lose + draw;
                game = new Game(user_id, win, lose, draw, totalgames);
                games.add(game);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public List<Game> calculateRank(List<Game> games) {
        for (Game game : games) {
            if (game.getTotalgames() == 0) {
                calOdds = 0;
            } else {
                calOdds = (float) (game.getWin() * 100 / game.getTotalgames());
            }
            game.setOdds(calOdds);
            game = new Game(game.getUser_id(), game.getOdds());
            oddsRate.add(game);
        }
        Game dumy;
        calGames.add(oddsRate.get(0));
        for (int i = 0; i < games.size() - 1; i++) {
            for (int j = i + 1; j < games.size(); j++) {
                if (oddsRate.get(i).getOdds() <= oddsRate.get(j).getOdds()) {
                    dumy = oddsRate.get(i);
                    oddsRate.set(i, oddsRate.get(j));
                    oddsRate.set(j, dumy);
                }

            }
        }


        return oddsRate;

    }

	
    
//    ===============================================================================
    
    public int logout(String userName, String password) {
		// TODO Auto-generated method stub
    		String loginCheckQuery = "SELECT is_login FROM user WHERE user_name = ? AND password = ?";
    		if (checkUserExistence(loginCheckQuery, userName, password)) {
    			
    			LocalDateTime now = LocalDateTime.now();
    			String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    			String logoutQuery = String.format("UPDATE user SET is_login = 0 WHERE user_name = ? AND password = ?");
    			
    			try (Connection connection = DBUtils.getConnection();
    					 PreparedStatement preparedStatement = createPreparedStatement(connection, logoutQuery, userName, password);) {
    					
    					addLoginLog(userName, formattedNow);
    					return preparedStatement.executeUpdate();
    				} catch (Exception e) {
    					// TODO: handle exception
    					e.printStackTrace();
    				}
    			}

    			return 0;
    		}
    
    

    private int addLoginLog(String userName, String now) {
		// TODO Auto-generated method stub
		String addLogQuery;

		int updatedRow = 0;
		addLogQuery = String.format("UPDATE login_log SET logout_time = \"%s\" WHERE user_id = ? and logout_time IS NULL", now);
		
		try (Connection connection = DBUtils.getConnection();
			 PreparedStatement preparedStatement = createPreparedStatement(connection, addLogQuery, findUserIdNyName(userName));) {
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return updatedRow;

	}
    

	private int findUserIdNyName(String userName) {
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

	private PreparedStatement createPreparedStatement(Connection connection, String query, int userId) throws SQLException {
		// TODO Auto-generated method stub
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, userId);
		return preparedStatement;
	}
}