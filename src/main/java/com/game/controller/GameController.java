package com.game.controller;

import java.util.Scanner;

import com.game.service.GameService;
import com.game.view.GameView;

public class GameController {

	private final GameService gameService;
	private final GameView gameView;
	private Exception errorObject;
	Scanner sc = new Scanner(System.in);

	public GameController() {
		this.gameService = new GameService();
		this.gameView = new GameView();
	}
	
	public void joinUser() {
		String name;
		while (true) {
			gameView.joinUserInputName();
			name = sc.next();
			System.out.println("입력한 이름은 "+name +" 입니다.");
			if (gameService.joinUserInputName(name)) {
				gameView.reInput();
			} else {
				break;
			}
		}
		gameView.joinUserInputPassword();
		String password = sc.next();
		save(0, name,password,0);
	}
	
	public void save(int userId, String userName, String password, int isLogin) {
		
		int result = gameService.save(userId, userName, password, isLogin);
		// 데이터의 저장 성공여부는 gameView가 출력한다.
		if (result > 0) {
			gameView.successPage();
		} else {
			errorObject = new Exception("Database 등록 실패");
			gameView.errorPage(errorObject);
		}
	}


}
