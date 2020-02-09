package com.twu.entities;

public class Book extends MediaEntity {

    public Book(String name, String author, int year) {
        super(name, author, year);
    }

    @Override
    public String toString() {
        return getName() + " | " + getAuthor() + " | " + getYear();
    }
}
