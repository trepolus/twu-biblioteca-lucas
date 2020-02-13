package com.twu.service;

import com.twu.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserServiceImplTest {

    UserService userService;

    @Before
    public void init() {
        this.userService = new UserServiceImpl();
    }

    @Test
    public void shouldSuccesfullyLoginAndReturnCorrectUser() {

        User mockUser = new User("906-1189", "filmAddict99", "ilovefilms@gmx.de", "+49391003927");
        String mockPassword = "pulpfiction";

        User actualuser = userService.login(mockUser.getLibraryId(), mockPassword);

        assertThat(actualuser.getLibraryId(), is(mockUser.getLibraryId()));
        assertThat(actualuser.getName(), is(mockUser.getName()));
        assertThat(actualuser.getEmail(), is(mockUser.getEmail()));
        assertThat(actualuser.getPhoneNumber(), is(mockUser.getPhoneNumber()));
    }

    @Test
    public void shouldFailToLoginAndReturnNull() {

        User mockUser = new User("111-1111", "fakeName", "wrong@email.de", "+00000000");
        String mockPassword = "fakepwd";

        User actualuser = userService.login(mockUser.getLibraryId(), mockPassword);

        assertThat(actualuser, is(nullValue()));

    }
}
