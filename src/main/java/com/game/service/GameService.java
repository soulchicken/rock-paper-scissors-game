package com.game.service;

import com.game.dao.GameDAO;

public class GameService {
	private final GameDAO gameDAO;
	
	public GameService() {
		this.gameDAO = new GameDAO();
	}
	
	public int save(int userId, String userName, String password, int isLogin) {
		// TODO Auto-generated method stub
		return gameDAO.save(userId, userName, password, isLogin);
	}

}
