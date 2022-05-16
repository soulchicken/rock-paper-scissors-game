package com.game.service;

import com.game.dao.GameDAO;

public class GameService {
	
	private final GameDAO gameDAO;

	/**
	 * 
	 */
	public GameService() { 
		this.gameDAO = new GameDAO();
		
	}


	public int login(String userId, String password) {
		// TODO Auto-generated method stub
		return gameDAO.login(userId, password);
	}

	public int logout(String userId, String password) {
		// TODO Auto-generated method stub
		return gameDAO.logout(userId, password);
	}

}
