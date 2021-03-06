package com.twu.entities;

public abstract class MediaEntity {

    private String name;
    private int year;
    private int id;
    private boolean checkedOut;

    public MediaEntity(String name, int year, int id) {
        this.name = name;
        this.year = year;
        this.id = id;
        this.checkedOut = false;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
