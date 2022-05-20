package com.game.controller;

import com.game.dao.GameDAO;
import com.game.model.Game;

import com.game.model.Stage;
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
    private GameDAO gameDAO;


    public RankController() {
        this.rankView = new RankView();
        this.rankService = new RankService();
        this.menuview = new MenuView();

        this.user = new User();
        this.gameDAO = new GameDAO();


    }

    public void gameMenu(String userName) {
        rankView.gameMenu();
        chooseNumber(userName);
    }
    public void playGame(String userName){
        rankView.playGame();
        Stage.gameStart(gameDAO.findUserIdNyName(userName));
        
        rankView.playingMenu();
        if(rankView.getNumberChoice() == 1) {
            showRank();
        }else if(rankView.getNumberChoice() == 2) {
            logout(userName);
        }
    }
    public void showRank() {
        games = rankService.showRank();
        games = rankService.calRank(games);
        rankView.calRank(games);

    }
    public void logout(String userName) {
//        user.setIs_login(false);
//        gameController.logout(user.getId(), user.getPassword());
    		rankService.logout(userName);
        menuview.logout();

    }
    public void chooseNumber(String userName) {
        if(rankView.getNumberChoice() == 1) {
            playGame(userName);
        }else if(rankView.getNumberChoice() == 2) {
            showRank();
        }else if(rankView.getNumberChoice() == 3) {
            logout(userName);
        }
    }
}