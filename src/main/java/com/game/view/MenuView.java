package com.game.view;

import com.game.model.User;
import lombok.Getter;

import java.util.Scanner;

@Getter
public class MenuView {
    private Scanner sc;
    private User user;
    private int numberChoice;

    public MenuView(){
        this.sc = new Scanner(System.in);
        this.user = new User();
    }
    public int getNumberChoice() {
		return numberChoice;
	}
	public void mainPage() {
    	System.out.println(">>1.로그인 2.회원가입");
    	System.out.print(">>");
    	numberChoice = sc.nextInt();
    }
    public void loginpage(){
        System.out.println(">>로그인을 진행해주세요");
        System.out.println(">>아이디를 입력하세요");
        System.out.print(">>");
        user.setUserName(sc.next());
        System.out.println(">>비밀번호를 입력하세요");
        System.out.print(">>");
        user.setPassword(sc.next());

    }


	public void logout() {
		System.out.println(">>로그아웃 되었습니다");
	}

}
