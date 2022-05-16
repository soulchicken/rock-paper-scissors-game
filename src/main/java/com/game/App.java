
package com.game;

import com.game.controller.GameController;
import com.game.controller.MenuController;


public class App {

	public static void main(String[] args) {
  	GameController gameController = new GameController();
//		gameController.joinUser();
		MenuController menuController = new MenuController();
		menuController.main();
	}

}