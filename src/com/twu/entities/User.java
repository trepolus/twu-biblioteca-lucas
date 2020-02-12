package com.twu.entities;

public class User {

    private String libraryId;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryId, String name, String email, String phoneNumber) {
        this.libraryId = libraryId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
