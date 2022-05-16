package com.game.controller;

import com.game.model.User;
import com.game.view.MenuView;

public class MenuController {
    private final MenuView menuView;
    private final User user;
    private final GameController gameController;
    public MenuController(){
        this.menuView = new MenuView();
        this.user = new User();
        this.gameController = new GameController();
       
    }
    public void main() {
    	menuView.mainPage();
    	if(menuView.getNumberChoice() == 1) {
    		login();
    	}else if(menuView.getNumberChoice() == 2) {
    		System.out.println("회원가입");
    		signUp();
    	}
    	
    }
    public void login(){
        menuView.loginpage();
        //아이디 비밀번호 입력 성공, 실패 관련 if문 작성예정
        if(true) {
        	menuView.loginSuccess();
        	user.setIs_login(true);
        	//게임 컨트롤러로 이동
        	gameController.gameMenu();
        	
        }else {
        	menuView.loginError();
        	user.setIs_login(false);
        }
    }
    public void signUp() {
    	//회원가입 컨트롤러
    	menuView.signup();
    	if(true) {
        	menuView.signupSuccess();
        	login();
        	
        	//게임 컨트롤러로 이동
        }else {
        	menuView.signupError();
        }
    }
    

}
