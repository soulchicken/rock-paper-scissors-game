package com.game;

import com.game.controller.GameController;

public class App {

	public static void main(String[] args) {
		GameController gameController = new GameController();

//		todoController.save(todo);
//		System.out.println("\n\n---------ºº¿Ã∫Í »Æ¿Œ");
		gameController.joinUser();
		gameController.save(2,"±Ë±Ë±Ë","1234",0);
	}

}
