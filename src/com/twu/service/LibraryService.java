package com.twu.service;

import com.twu.entities.Library;
import com.twu.entities.MediaEntity;

import java.util.List;

public interface LibraryService {

    Library createAndFillLibraryWithBooks(Library library);

    Library createAndFillLibraryWithMovies(Library library);

    Library getLibraryByName(String name);

    List<MediaEntity> getAllMediaEntitiesByLibraryName(String name);

    Library getLibraryById(int id);

    List<MediaEntity> getAllMediaEntitiesByLibraryId(int id);

    List<Library> getAllLibraries();

    boolean checkOutMediaEntityByIdFromLibraryById(int libraryId, int mediaEntityId, String userId);

    boolean returnMediaEntityByIdToLibraryById(int libraryId, int mediaEntityId, String userId);
}
