package com.game.controller;

import com.game.view.GameView;



public class GameController {
	private final GameView gameView;
	

	public GameController() {
		this.gameView = new GameView();
		
	}

	public void playGame(){
		gameView.playGame();
    }
}
