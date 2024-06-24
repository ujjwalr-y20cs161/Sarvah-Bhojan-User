package com.example.fooddeliveryuser.services;

public class Tokens {
    private static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String LOGGED = "logged";

    public static String getSharedPrefName(){
        return SHARED_PREF_NAME;
    }

    public static String getKeyUsername(){
        return KEY_USERNAME;
    }

    public static String getKeyPassword(){
        return KEY_PASSWORD;
    }

    public static String getLogged(){
        return LOGGED;
    }

}
