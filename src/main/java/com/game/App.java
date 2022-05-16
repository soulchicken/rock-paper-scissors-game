package com.game;

import com.game.controller.GameController;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameController gameController = new GameController();
		gameController.login("sdsd", "ㅇㄴ'");
		gameController.login("root", "1234");
		gameController.logout("root", "1234");

	}

}
