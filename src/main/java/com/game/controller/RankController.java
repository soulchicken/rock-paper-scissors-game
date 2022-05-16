package com.game.controller;

import com.game.model.Game;
import com.game.model.User;

import java.util.List;



import com.game.service.RankService;
import com.game.view.MenuView;
import com.game.view.RankView;

public class RankController {
    private final RankView rankView;
    private final RankService rankService;
    private final MenuView menuview;
    private List<Game> games;
    private User user;

    public RankController() {
        this.rankView = new RankView();
        this.rankService = new RankService();
        this.menuview = new MenuView();
        this.user = new User();

    }

    public void gameMenu() {
        rankView.gameMenu();
        chooseNumber();
    }
    public void playGame(){
        rankView.playGame();
        rankView.playingMenu();
        if(rankView.getNumberChoice() == 1) {
            showRank();
        }else if(rankView.getNumberChoice() == 2) {
            logout();
        }
    }
    public void showRank() {
        games = rankService.showRank();
        games = rankService.calRank(games);
        rankView.calRank(games);

    }
    public void logout() {
        menuview.logout();
        user.setIs_login(false);

    }
    public void chooseNumber() {
        if(rankView.getNumberChoice() == 1) {
            playGame();
        }else if(rankView.getNumberChoice() == 2) {
            showRank();
        }else if(rankView.getNumberChoice() == 3) {
            logout();
        }
    }
}