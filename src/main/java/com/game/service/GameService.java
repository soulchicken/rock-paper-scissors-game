package com.game.service;

import com.game.dao.GameDAO;
import com.game.model.Game;
import java.util.List;

public class GameService {
	private final GameDAO gameDAO;
	
	public GameService() {
		this.gameDAO = new GameDAO();
	}
	
	public int save(int userId, String userName, String password, int isLogin) {
		int result = gameDAO.saveUser(userId, userName, password, isLogin);
		gameDAO.saveScore(userName);
		return result;
	}

	public boolean joinUserInputName(String name) {
		return gameDAO.checkUserId(name);
  }
  	public List<Game> showRank() {
		return gameDAO.showRank();
	}
  
	public List<Game> calRank(List<Game> games){
		return gameDAO.calculateRank(games);

	}
  
  	public int login(String userId, String password) {
		// TODO Auto-generated method stub
		return gameDAO.login(userId, password);
	}

	public int logout(String userId, String password) {
		// TODO Auto-generated method stub
		return gameDAO.logout(userId, password);
	}
}