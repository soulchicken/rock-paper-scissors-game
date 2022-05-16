package com.game.controller;

import com.game.model.Game;
import java.util.List;



import com.game.service.RankService;
import com.game.view.MenuView;
import com.game.view.RankView;

public class RankController {
    private final RankView rankView;
    private final RankService rankService;
    private final MenuView menuview;
    private List<Game> games;
    

    public RankController() {
        this.rankView = new RankView();
        this.rankService = new RankService();
        this.menuview = new MenuView();
        
        

    }

    public void gameMenu(String userName, String password) {
        rankView.gameMenu();
        chooseNumber(userName, password);
    }
    public void playGame(String userName, String password){
        rankView.playGame();
        rankView.playingMenu();
        if(rankView.getNumberChoice() == 1) {
            showRank();
        }else if(rankView.getNumberChoice() == 2) {
            logout(userName, password);
        }
    }
    public void showRank() {
        games = rankService.showRank();
        games = rankService.calRank(games);
        rankView.calRank(games);

    }
    public void logout(String userName, String password) {
//        user.setIs_login(false);
//        gameController.logout(user.getId(), user.getPassword());
    		rankService.logout(userName, password);
        menuview.logout();

    }
    public void chooseNumber(String userName, String password) {
        if(rankView.getNumberChoice() == 1) {
            playGame(userName, password);
        }else if(rankView.getNumberChoice() == 2) {
            showRank();
        }else if(rankView.getNumberChoice() == 3) {
            logout(userName, password);
        }
    }
}