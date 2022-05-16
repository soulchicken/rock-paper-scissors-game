package com.game.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 인스턴스 생성 방지
@AllArgsConstructor
@Getter
@ToString
@lombok.Builder
public class Game {
	private String userId;
	private String password;
	private Boolean isLogin;

}
