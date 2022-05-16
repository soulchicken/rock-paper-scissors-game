package com.game.view;

import java.util.Scanner;

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

  public void login(String string) {
		System.out.println(string);
	}


public void successPage() {
		System.out.println("정상 등록되었습니다.");
	}

	public void errorPage(Exception errorObject) {
		System.out.println("문제가 발생하였습니다. "+ errorObject.getMessage());
	}

	public void joinUserInputName() {
		System.out.println("유저 이름을 입력해주세요.");
	}

	public void joinUserInputPassword() {
		System.out.println("비밀번호을 입력해주세요.");
	}

	public void reInput() {
		System.out.println("중복된 값입니다. 다시 입력해주세요.");
	}


	public void logout(String s) {
		System.out.println(s);
	}
}
