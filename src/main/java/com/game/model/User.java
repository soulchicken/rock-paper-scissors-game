package com.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private int userId;
    private String password;
    private Boolean is_login;

    @Override
    public String toString() {
        return "User{" +
                "id='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", is_login=" + is_login +
                '}';
    }
}