package com.twu.entities;

public class User {

    private int libraryId;
    private String name;
    private String email;
    private String phoneNumber;

    public User(int libraryId, String name, String email, String phoneNumber) {
        this.libraryId = libraryId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
