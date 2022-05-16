package com.game.controller;

import com.game.service.GameService;
import com.game.view.GameView;

public class GameController {

	private GameService gameService;
	private GameView gameView;

	public GameController() {
		super();
		this.gameView = new GameView();
		this.gameService = new GameService();
	}

	/**
	 * 로그인 메서드
	 * @param userId
	 * @param password
	 */
	public void login(String userId, String password) {
		// TODO Auto-generated method stub
		if (gameService.login(userId, password) != 0) {
			gameView.login("로그인되었습니다. ");
		}
		else {
			gameView.login("없는 회원정보입니다.");
		}
	}


	/** 
	 * 로그아웃 메서드
	 * @param userId
	 * @param password
	 */
	public void logout(String userId, String password) {
		// TODO Auto-generated method stub
		if (gameService.logout(userId, password) != 0) {
			gameView.logout("로그아웃되었습니다.");
		}
		else {
			gameView.logout("잠시 후 다시 로그아웃해주세요");
		}

	}
}
