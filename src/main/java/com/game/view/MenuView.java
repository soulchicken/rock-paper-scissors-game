package com.game.view;

import com.game.model.User;
import lombok.Getter;

import java.util.Scanner;

@Getter
public class MenuView {
    private Scanner sc;
    private final User user;
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
        user.setId(sc.next());
        System.out.println(">>비밀번호를 입력하세요");
        System.out.print(">>");
        user.setPassword(sc.next());

        //아이디 비밀번호 확인
        System.out.println(user.toString());
    }
    public void loginSuccess() {System.out.println(">>로그인에 성공하였습니다");}
    public void loginError() {
    	System.out.println(">>로그인에 실패하였습니다.");
    	System.out.println(">>아이디와 비밀번호를 다시 입력해주세요");
    }
    public void signup() {
    	System.out.println(">>회원가입을 진행해주세요.");
    	//회원가입 관련 view
    }
    public void signupSuccess() {System.out.println(">>회원가입에 성공하였습니다");}
    public void signupError() {
    	System.out.println(">>회원가입에 실패하였습니다.");
    	System.out.println(">>아이디와 비밀번호를 다시 입력해주세요");
    }
	public void logout() {
		System.out.println(">>로그아웃 되었습니다");
	}

}
