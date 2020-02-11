package com.twu.entities;

public abstract class MediaEntity {

    private String name;
    private String author;
    private int year;
    private int id;
    private boolean checkedOut;

    public MediaEntity(String name, String author, int year, int id) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public boolean isCheckedOut(){
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
