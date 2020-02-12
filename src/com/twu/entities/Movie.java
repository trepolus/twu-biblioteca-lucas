package com.twu.entities;

public class Movie extends MediaEntity{

    private String director;
    private int rating;

    public Movie(int id, String name, int year, String director, int rating) {
        super(name, year, id);
        this.rating = rating;
        this.director = director;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
