package com.twu.entities;

public abstract class MediaEntity {

    private String name;
    private String author;
    private int year;

    public MediaEntity(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
