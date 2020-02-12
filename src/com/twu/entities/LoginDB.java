package com.twu.entities;

import java.util.HashMap;
import java.util.Map;

public class LoginDB {

    private Map<String, String> loginCredentials;

    public LoginDB() {
        this.loginCredentials = new HashMap<>();
        initLoginDB();
    }

    private void initLoginDB() {
        loginCredentials.put("Hans123","login123");
        loginCredentials.put("FilmFanatic", "pulpfiction");
        loginCredentials.put("Cool Guy", "password123");
        loginCredentials.put("LucasK", "fy839ui4fju3h90ifj9u3hji0 woiuh9ji*&%");
    }

    public boolean authenticate(String user, String password){
        if (loginCredentials.containsKey(user)){
            if (loginCredentials.get(user).equals(password)){
                return true;
            }
        }
        return false;
    }


}
