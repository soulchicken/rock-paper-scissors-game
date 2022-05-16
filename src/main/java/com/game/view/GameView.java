package com.game.view;

public class GameView {

	public void successPage() {
		System.out.println("정상 등록되었습니다.");
	}

	public void errorPage(Exception errorObject) {
		System.out.println("문제가 발생하였습니다. "+errorObject.getMessage());
	}
	
}
