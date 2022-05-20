package com.game.model;


import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.game.dao.Dao;


public class Stage {
	public static void gameStart(int id) {

		Scanner scanner = new Scanner(System.in);
		Dao dao = new Dao();
		final int GAME_END_NUMBER = 0;
		final int GAME_START_NUMBER = 1; 
		
		System.out.println("가위바위보 게임을 시작하시겠습니까? 0.NO 1.YES");
	
		//승률 초기값
		int[] scores = new int[3];
		
		scores = dao.loadScore(id);
		
		int winScore = scores[0];
		int loseScore = scores[1];
		int drawScore = scores[2];
	
		//가위바위보 게임 반복문
		while (true) {
			
			
			//0:게임종료, 1:게임시작
			int cn = scanner.nextInt();

			if (cn == GAME_END_NUMBER) {
				System.out.println("게임을 종료합니다.");
				System.out.println("최종 스코어 - 이긴 횟수 : " + winScore + ", 진 횟수 : " + loseScore + ", 비긴 횟수 : " + drawScore);
				dao.save(id, winScore, loseScore, drawScore);
				
				break;
								
			} else if (cn == GAME_START_NUMBER) {
				
				System.out.println("안내면 진거 가위바위보!");
				System.out.println("입력하세요 : 1.가위, 2.바위, 3.보");
				
				int input = scanner.nextInt();// 입력

				Random rd = new Random();
				int comCase = rd.nextInt(3) + 1;

				// 유저, 컴퓨터 입력값 변형
				HashMap<Integer, String> hMap = new HashMap<>();
				hMap.put(1, "가위");
				hMap.put(2, "바위");
				hMap.put(3, "보");

				// 선택 출력
				System.out.println("유저의 선택 :: " + hMap.get(input));
				System.out.println("컴퓨터의 선택 :: " + hMap.get(comCase));
				

				// 승자 결정
				String[] rsWinner = { "WIN!!", "LOSE", "DRAW" };
				int rsInt = winner(input, comCase);
				System.out.println(rsWinner[rsInt]);
				
				// 승률 ++
				if (rsInt == 0) {
					winScore++;
				} else if(rsInt == 1) {
					loseScore++;
				} else {
					drawScore++;
				}
				System.out.println("이긴 횟수 : " + winScore + ", 진 횟수 : " + loseScore + ", 비긴 횟수 : " + drawScore);

				// 계속 할거임?
				System.out.println("게임을 계속 하시겠습니까? 0.No 1.Yes");


			} else {
				System.out.println("0, 1 중 하나만 입력해주세요.");
			}
		}
	}

	public static int winner(int user, int com) {
		int answer = 0;

		if (user == com) 
			return 2;
		
		if (user == 0 && com == 1) {
			answer = 1;

		} else if (user == 1 && com == 2) {
			answer = 1;

		} else if (user == 2 && com == 0) {
			answer = 1;

		}
		return answer;
	}

}
