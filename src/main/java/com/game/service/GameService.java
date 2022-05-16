package com.game.service;

import java.util.List;

import com.game.dao.GameDAO;
import com.game.model.Game;

public class GameService {
	private final GameDAO gameDAO;
	public GameService() {
		this.gameDAO = new GameDAO();
	}

	public List<Game> showRank() {
		return gameDAO.showRank();
	}

}
