package com.game.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class hwangDBUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "game";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        Connection connetion = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER,PASSWORD);
        return connetion;
    }



}
