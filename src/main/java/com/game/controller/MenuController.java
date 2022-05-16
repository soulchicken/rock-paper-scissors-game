package com.game.controller;

import com.game.model.Game;
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
            menuView.loginpage();
            user.setId(menuView.getUser().getId());
            user.setPassword(menuView.getUser().getPassword());
            gameController.login(user.getId(), user.getPassword());

    	}else if(menuView.getNumberChoice() == 2) {
            System.out.println(">>회원가입을 진행하겠습니다");
            gameController.joinUser();
            menuView.loginpage();
            user.setId(menuView.getUser().getId());
            user.setPassword(menuView.getUser().getPassword());
            gameController.login(user.getId(), user.getPassword());
    	}
    	
    }


}