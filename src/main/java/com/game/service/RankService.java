package com.game.service;

import com.game.dao.RankDAO;
import com.game.model.Game;
import java.util.List;

public class RankService {
	private final RankDAO rankDAO;

	public RankService() {
		this.rankDAO = new RankDAO();
	}


	public List<Game> showRank() {
		return rankDAO.showRank();
	}

	public List<Game> calRank(List<Game> games){
		return rankDAO.calculateRank(games);

	}

	public int logout(String userName, String password) {
		// TODO Auto-generated method stub
		return rankDAO.logout(userName, password);
	}

}