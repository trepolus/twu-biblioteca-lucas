package com.twu.entities;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<MediaEntity> mediaEntityList;
    private String name;
    private int id;

    public Library(List<MediaEntity> mediaEntityList, String name, int id) {
        this.mediaEntityList = mediaEntityList;
        this.name = name;
        this.id = id;
    }

    public Library(String name, int id) {
        this.mediaEntityList = new ArrayList<>();
        this.name = name;
        this.id = id;
    }

    public List<MediaEntity> getMediaEntityList() {
        return mediaEntityList;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
