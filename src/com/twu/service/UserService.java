package com.twu.service;

import com.twu.entities.User;

public interface UserService {

    User login(String libraryId, String password);
}
