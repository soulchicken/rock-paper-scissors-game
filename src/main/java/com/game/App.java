package com.game;

import com.game.controller.GameController;

public class App {

	public static void main(String[] args) {
		GameController gameController = new GameController();

		// 회원 가입 기능
		gameController.joinUser();
	}

}
