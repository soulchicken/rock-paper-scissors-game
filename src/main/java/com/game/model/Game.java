package com.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
	int user_id;
	int win;
	int lose;
	int draw;
	int totalgames = win +  lose + draw;
	float odds;
	
	public Game(int user_id, int win, int lose, int draw, int totalgames) {
		this.user_id = user_id;
		this.win = win;
		this.lose = lose;
		this.draw = draw;
		this.totalgames = totalgames;
	}
	public Game(int user_id,float odds) {
		this.user_id = user_id;
		this.odds = odds;
	}
}
