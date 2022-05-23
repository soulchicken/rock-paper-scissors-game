package com.game.view;

public class StageView {
	public void ScoreResult(int winScore, int loseScore, int drawScore) {
		System.out.println("이긴 횟수 : " + winScore + ", 진 횟수 : " + loseScore + ", 비긴 횟수 : " + drawScore);
	}
	
	public void StageResult(int rsInt) {
		String[] rsWinner = { "WIN!!", "LOSE", "DRAW" };
		System.out.println(rsWinner[rsInt]);
	}
}
