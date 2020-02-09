package com.twu.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

    private List<MediaEntity> mediaEntityList;
    private String name;

    public Library(List<MediaEntity> mediaEntityList, String name) {
        this.mediaEntityList = mediaEntityList;
        this.name = name;
    }

    public Library(String name) {
        this.mediaEntityList = new ArrayList<>();
        this.name = name;
    }

    public List<MediaEntity> getMediaEntityList() {
        return mediaEntityList;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(mediaEntityList, library.mediaEntityList) &&
                Objects.equals(name, library.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaEntityList, name);
    }
}
