package com.twu.entities;

public class Book extends MediaEntity {

    private String author;

    public Book(String name, String author, int year, int id) {
        super(name, year, id);
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }
}
