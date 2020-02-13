package com.twu.service;

import com.twu.authentication.LoginService;
import com.twu.authentication.LoginServiceImpl;
import com.twu.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private List<User> userDatabase;
    private Map<String, Boolean> loggedInUsers;

    private LoginService loginService;


    public UserServiceImpl() {
        this.userDatabase = new ArrayList<>();
        this.loginService = new LoginServiceImpl();
        this.loggedInUsers = new HashMap<>();

        initUserDatabase();
    }

    public User login(String libraryId, String password) {
        if (loginService.authenticate(libraryId, password)) {
            User user = getUserByLibraryId(libraryId);

            if (user != null) {
                loggedInUsers.put(libraryId, true);
                return user;
            }
        }
        return null;
    }

    private void initUserDatabase() {
        User user1 = new User("543-2255", "Master123", "master123@gmail.com", "+493382910466");
        User user2 = new User("906-1189", "filmAddict99", "ilovefilms@gmx.de", "+49391003927");
        User user3 = new User("499-0102", "Dr. Wolgang Roth", "wolgang@roth.at", "+431423536");
        User user4 = new User("000-9999", "B00kG@ng$t@h", "hacker@net.org", "+11111111111");
        User user5 = new User("000-0000", "tester", "test@net.org", "+11111111111");

        userDatabase.add(user1);
        userDatabase.add(user2);
        userDatabase.add(user3);
        userDatabase.add(user4);
        userDatabase.add(user5);
    }

    private User getUserByLibraryId(String libraryId) {
        for (User user : userDatabase) {
            if (user.getLibraryId().equals(libraryId)) {
                return user;
            }
        }
        return null;
    }

}
