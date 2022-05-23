package com.game.view;

import java.util.HashMap;

public class StageView {
	public void stageStart() {
		System.out.println("안내면 진거 가위바위보!");
		System.out.println("입력하세요 : 1.가위, 2.바위, 3.보");
	}
	
	public void ScoreResult(int winScore, int loseScore, int drawScore) {
		System.out.println("이긴 횟수 : " + winScore + ", 진 횟수 : " + loseScore + ", 비긴 횟수 : " + drawScore);
	}
	
	public void ChooseOnesHand(int input, int comCase, HashMap<Integer, String> hMap) {
		System.out.println("유저의 선택 :: " + hMap.get(input));
		System.out.println("컴퓨터의 선택 :: " + hMap.get(comCase));
	}

	
	public void StageResult(int rsInt) {
		String[] rsWinner = { "WIN!!", "LOSE", "DRAW" };
		System.out.println(rsWinner[rsInt]);
	}
	
	public void EndOfStage(int winScore, int loseScore, int drawScore) {
		System.out.println("게임을 종료합니다.");
		System.out.println("최종 스코어 - 이긴 횟수 : " + winScore + ", 진 횟수 : " + loseScore + ", 비긴 횟수 : " + drawScore);
	}
}
