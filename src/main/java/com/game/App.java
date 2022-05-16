
package com.game;

import com.game.controller.GameController;
import com.game.model.Stage;


public class App {

	public static void main(String[] args) {
		GameController gameController = new GameController();
//		gameController.joinUser();
		Stage.gameStart();
	}

}