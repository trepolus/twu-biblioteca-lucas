package com.twu.authentication;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginServiceImplTest {

    LoginService loginService;

    @Before
    public void init() {
        this.loginService = new LoginServiceImpl();
    }

    @Test
    public void shouldSuccessfullyAuthenticate() {

        String mockUserName = "543-2255";
        String mockPassword = "login123";

        assertThat(loginService.authenticate(mockUserName, mockPassword), is(true));
    }

    @Test
    public void shouldFailAuthentication() {

        String mockUserName = "88888";
        String mockPassword = "k0ij9h8";

        assertThat(loginService.authenticate(mockUserName, mockPassword), is(false));
    }
}
