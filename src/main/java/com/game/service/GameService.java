package com.game.service;

import com.game.dao.GameDAO;
import com.game.view.GameView;

public class GameService {
	
	private final GameDAO gameDAO;
	private final GameView gameView;
	
	

	/**
	 * 
	 */
	public GameService() {
		this.gameDAO = new GameDAO();
		this.gameView = new GameView();
		
	}



	public int login(String userId, String password) {
		// TODO Auto-generated method stub
		
		gameView.login("controller -> service");
		int i = gameDAO.login(userId, password);
		gameView.login("DAO -> service ");
		
		return i;
		
	}



	public int logout(String userId, String password) {
		// TODO Auto-generated method stub
		
		gameView.logout("controller -> service");
		int i = gameDAO.logout(userId, password);
		gameView.logout("DAO -> service ");
		return i;
		
	}

}
