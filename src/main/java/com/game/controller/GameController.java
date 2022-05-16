package com.game.controller;

import java.util.List;

import com.game.model.Game;
import com.game.service.GameService;
import com.game.view.GameView;



public class GameController {
	private final GameView gameView;
	private final GameService gameService;

	private List<Game> games;

	public GameController() {
		this.gameView = new GameView();
		this.gameService = new GameService();
		
	}
	public void gameMenu() {
		gameView.gameMenu();
		if(gameView.getNumberChoice() == 1) {
			playGame();
    	}else if(gameView.getNumberChoice() == 2) {
    		showRank();
    	}
	}

	public void playGame(){
		gameView.playGame();
    }
	public void showRank() {
		games = gameService.showRank();
		gameView.ShowRankPage(games);
	}
}
