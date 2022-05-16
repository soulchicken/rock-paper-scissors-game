package com.game.controller;


import java.util.List;
import com.game.model.Game;
import com.game.model.User;
import com.game.service.GameService;
import com.game.view.GameView;
import com.game.view.MenuView;

public class GameController {
	private final GameView gameView;
	private final GameService gameService;
	private final MenuView menuview;
	private List<Game> games;
	private User user;
	private GameService gameService;
	private GameView gameView;


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
}
