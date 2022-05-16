package com.game.view;

import java.util.List;
import java.util.Scanner;

import com.game.model.Game;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GameView {
	private Scanner sc;
	private int numberChoice;
	private int rate =1;
	public GameView() {
		this.sc = new Scanner(System.in);
	}
	public void gameMenu() {
		System.out.println(">>1.게임시작 2.랭킹보기 3.로그아웃");
		System.out.print(">>");
		numberChoice = sc.nextInt();
		
	}
	public void playingMenu() {
		System.out.println(">>1.랭킹보기 2.로그아웃 ");
		System.out.print(">>");
		numberChoice = sc.nextInt();
	}
    public void playGame(){
        System.out.println(">>게임을 시작했습니다. 여기다가 게임 실행화면 넣기");
        
    }
    public void ShowRankPage(List<Game> games) {
    	System.out.println("전체 랭킹");
    	for (Game game : games) {
    		
			System.out.println(String.format("아이디 %d, win %d, lose %d, draw %d total %d", 
					game.getUser_id(),game.getWin(),game.getLose(), game.getDraw(), game.getTotalgames()));
			System.out.println();
		}
    }
	public void calRank(List<Game> games) {

		System.out.println("|----------순위----------|");
    	for (Game game : games) {
 
			System.out.println(String.format("%d등 아이디 %d, odds %.0f 퍼센트" , 
					rate,game.getUser_id(),game.getOdds()));
			rate ++;
			System.out.println();
		}
	}
}
