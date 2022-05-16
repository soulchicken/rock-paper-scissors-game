package com.game.controller;
import java.util.Scanner;


import com.game.service.GameService;
import com.game.view.GameView;


public class GameController {
	private final GameView gameView;
	private final GameService gameService;
	private final RankController rankController;

  private Exception errorObject;
	Scanner sc = new Scanner(System.in);


	public GameController() {
		this.gameView = new GameView();
		this.gameService = new GameService();
		this.rankController = new RankController();


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
			rankController.gameMenu();
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