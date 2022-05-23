package com.game.model;


import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.game.dao.Dao;
import com.game.view.StageView;


public class Stage {
	// 1.가위, 2.바위, 3.보
	final static int SCISSORS = 1;
	final static int ROCK = 2;
	final static int PAPER = 3;
	
	final static int WIN = 0;
	final static int LOSE = 1;
	final static int DRAW = 2;
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void gameStart(int id) {
		StageView stageView = new StageView();

		int userSaidNumber;
		Dao dao = new Dao();
		final int GAME_END_NUMBER = 0;
		final int GAME_START_NUMBER = 1; 
		
		stageView.StageStart();
	
		//승률 초기값
		int[] scores = new int[3];
		
		scores = dao.loadScore(id);
		
		int winScore = scores[0];
		int loseScore = scores[1];
		int drawScore = scores[2];
	
		//가위바위보 게임 반복문
		while (true) {
			
			
			//0:게임종료, 1:게임시작
			userSaidNumber = scanner.nextInt();

			if (userSaidNumber == GAME_END_NUMBER) {
				stageView.EndOfStage(winScore, loseScore, drawScore);
				dao.save(id, winScore, loseScore, drawScore);
				
				break;
								
			} else if (userSaidNumber == GAME_START_NUMBER) {
				
				stageView.BattleStart();
				
				int input = scanner.nextInt();// 입력

				Random rd = new Random();
				int comCase = rd.nextInt(3) + 1;

				// 유저, 컴퓨터 입력값 변형
				HashMap<Integer, String> hMap = new HashMap<>();
				hMap.put(SCISSORS, "가위");
				hMap.put(ROCK, "바위");
				hMap.put(PAPER, "보");

				// 선택 출력
				stageView.ChooseOnesHand(input, comCase, hMap);
				

				// 승자 결정
				int rsInt = Winner(input, comCase);
				stageView.StageResult(rsInt);
				
				// 승점 ++
				if (rsInt == WIN) {
					winScore++;
				} else if(rsInt == DRAW) {
					loseScore++;
				} else {
					drawScore++;
				}
				stageView.ScoreResult(winScore, loseScore, drawScore);

				stageView.StageReStart();


			} else {
				stageView.stageInputErrorMassage();
			}
		}
	}


	public static int Winner(int user, int com) {
		if (user == com) 
			return DRAW;
		
		if (user == SCISSORS && com == ROCK) {
			return LOSE;

		} else if (user == ROCK && com == PAPER) {
			return LOSE;

		} else if (user == PAPER && com == SCISSORS) {
			return LOSE;

		}
		return WIN;
	}

}
