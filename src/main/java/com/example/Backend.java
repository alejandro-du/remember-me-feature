package com.example;

/**
 * @author Alejandro Duarte.
 */
public class Backend {

    public static boolean login(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }

}
