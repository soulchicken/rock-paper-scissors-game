package com.game.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.game.model.Game;
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
}