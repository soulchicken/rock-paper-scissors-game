package com.game;


import com.game.controller.GameController;
import com.game.controller.MenuController;

public class App {

	public static void main(String[] args) {
	
		GameController gameController = new GameController();
		gameController.login("sdsd", "ㅇㄴ'");
		gameController.login("root", "1234");
		gameController.logout("root", "1234");

		MenuController menuController = new MenuController();
		menuController.main();

	}

}