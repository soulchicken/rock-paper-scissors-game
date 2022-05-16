package com.game.service;

import com.game.dao.GameDAO;


public class GameService {
	private final GameDAO gameDAO;
	
	public GameService() {
		this.gameDAO = new GameDAO();
	}
	
	public int save(int userId, String userName, String password, int isLogin) {
		int result = gameDAO.saveUser(userId, userName, password, isLogin);
		gameDAO.saveScore(userName);
		return result;
	}

	public boolean joinUserInputName(String name) {
		return gameDAO.checkUserId(name);
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