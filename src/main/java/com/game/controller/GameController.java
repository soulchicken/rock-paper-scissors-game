package com.game.controller;

import com.game.service.GameService;
import com.game.view.GameView;

public class GameController {
	
	private GameView gameView;
	private GameService gameService;
	
	



	public GameController() {
		super();
		this.gameView = new GameView();
		this.gameService = new GameService();
	}




	public void login(String userId, String password) {
		// TODO Auto-generated method stub
		
		try {
			
			
			gameView.login("App -> controller");
			gameService.login(userId, password);
			gameView.login("service -> controller");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}




	public void logout(String userId, String password) {
		// TODO Auto-generated method stub
		
try {
			
			
			gameView.logout("App -> controller");
			gameService.logout(userId, password);
			gameView.logout("service -> controller");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
