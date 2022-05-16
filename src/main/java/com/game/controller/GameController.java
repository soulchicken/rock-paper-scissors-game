package com.game.controller;

import java.util.List;
import java.util.Scanner;

import com.game.service.GameService;
import com.game.view.GameView;
import com.game.model.Game;
import com.game.model.User;
import com.game.view.MenuView;


public class GameController {
	private final GameView gameView;
	private final GameService gameService;
	private final MenuView menuview;
	private List<Game> games;
	private User user;
  private Exception errorObject;
	Scanner sc = new Scanner(System.in);

	public GameController() {
		this.gameView = new GameView();
		this.gameService = new GameService();
		this.menuview = new MenuView();
		this.user= new User();
		
	}
	public void gameMenu() {
		gameView.gameMenu();
		chooseNumber();
	}
  	public void playGame(){
		gameView.playGame();
		gameView.playingMenu();
		if(gameView.getNumberChoice() == 1) {
			showRank();
    	}else if(gameView.getNumberChoice() == 2) {
    		logout();
    	}
    }
	public void showRank() {
		games = gameService.showRank();
		games = gameService.calRank(games);
		gameView.calRank(games);
		
	}
	public void logout() {
		menuview.logout();
		user.setIs_login(false);
		
	}
	public void chooseNumber() {
		if(gameView.getNumberChoice() == 1) {
			playGame();
    	}else if(gameView.getNumberChoice() == 2) {
    		showRank();
    	}else if(gameView.getNumberChoice() == 3) {
    		logout();
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