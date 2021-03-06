package com.twu.authentication;

import java.util.HashMap;
import java.util.Map;

public class LoginServiceImpl implements LoginService{

    private Map<String, String> loginCredentials;

    public LoginServiceImpl() {
        this.loginCredentials = new HashMap<>();
        initLoginDB();
    }

    private void initLoginDB() {
        loginCredentials.put("543-2255", "login123");
        loginCredentials.put("906-1189", "pulpfiction");
        loginCredentials.put("499-0102", "password123");
        loginCredentials.put("000-9999", "fy839ui4fju3h90ifj9u3hji0 woiuh9ji*&%");
        loginCredentials.put("000-0000", "p");

    }

    public boolean authenticate(String user, String password) {
        //check if loginName has the correct format
        if (!user.matches("\\d{3}-\\d{4}")) {
            return false;
        }

        if (loginCredentials.containsKey(user)) {
            return loginCredentials.get(user).equals(password);
        }
        return false;
    }
}
