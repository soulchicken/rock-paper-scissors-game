package com.game;

import com.game.controller.GameController;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameController gameController = new GameController();
		
//		gameController.login("zxc", "123'");
		
		gameController.logout("zxc", "123'");
		
		System.out.println(123);

	}

}
