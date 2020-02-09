package com.twu.service;

import com.twu.entities.Library;
import com.twu.entities.MediaEntity;

import java.util.ArrayList;
import java.util.List;

public interface LibraryService {

    public Library createAndFillLibraryWithBooks(Library library);

    public Library getLibraryByName(String name);

    public List<MediaEntity> getAllMediaEntitiesByLibraryName(String name);

    public List<Library> getAllLibraries();

}
