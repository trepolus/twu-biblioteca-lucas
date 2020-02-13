package com.twu.service;

import com.twu.entities.Book;
import com.twu.entities.Library;
import com.twu.entities.MediaEntity;
import com.twu.entities.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryServiceImpl implements LibraryService {

    private List<Library> libraryList;
    private Map<Integer, String> mediaEntityReservations;

    public LibraryServiceImpl() {
        this.libraryList = new ArrayList<>();
        this.mediaEntityReservations = new HashMap<>();
    }

    @Override
    public Library createAndFillLibraryWithBooks(Library library) {

        if (library == null) {
            // create new books and add them to a new library
            ArrayList<MediaEntity> bookList = new ArrayList<>();

            Book book1 = new Book("Refactoring", "Martin Fowler", 1998, 1);
            Book book2 = new Book("Life is good", "Unicorn Gorilla", 2019, 2);
            Book book3 = new Book("Design Patterns for Noobs", "Lucas Kummer", 2026, 3);

            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);

            library = new Library(bookList, "TW Library", 1);
        }
        libraryList.add(library);
        return library;
    }

    @Override
    public Library createAndFillLibraryWithMovies(Library library) {
        if (library == null) {
            // create new books and add them to a new library
            ArrayList<MediaEntity> movieList = new ArrayList<>();

            Movie movie1 = new Movie(1,"Pulp Fiction", 1994, "Quentin Tarantino", 10);
            Movie movie2 = new Movie(2,"Inglorious Basterds", 2009, "Quentin Tarantino", 9);
            Movie movie3 = new Movie(3,"Sharknado 6", 2018, "Anthony Ferrante", 2);
            Movie movie4 = new Movie(4,"La vita e bella", 1997, "Roberto Benigni", 9);
            Movie movie5 = new Movie(5,"Schindler's List", 1993, "Steven Spielberg", 9);
            Movie movie6 = new Movie(6,"Saw III", 2006, "Darren Bousman", 5);
            Movie movie7 = new Movie(7,"The Wolf of Wall Street", 2013, "Martin Scorsese", 8);
            Movie movie8 = new Movie(8,"One Flew Over the Cuckoo's Nest", 1975, "Miloš Forman", 0);

            movieList.add(movie1);
            movieList.add(movie2);
            movieList.add(movie3);
            movieList.add(movie4);
            movieList.add(movie5);
            movieList.add(movie6);
            movieList.add(movie7);
            movieList.add(movie8);

            library = new Library(movieList, "Movie Library", 1);
        }
        libraryList.add(library);
        return library;
    }


    @Override
    public Library getLibraryByName(String name) {
        if (!libraryList.isEmpty()) {
            for (Library library : libraryList) {
                if (library.getName().equals(name)) {
                    return library;
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaEntity> getAllMediaEntitiesByLibraryName(String name) {
        Library library = getLibraryByName(name);
        if (library != null) {
            return library.getMediaEntityList();
        }
        return null;
    }


    @Override
    public Library getLibraryById(int id) {
        if (!libraryList.isEmpty()) {
            for (Library library : libraryList) {
                if (library.getId() == id) {
                    return library;
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaEntity> getAllMediaEntitiesByLibraryId(int id) {
        Library library = getLibraryById(id);
        if (library != null) {
            return library.getMediaEntityList();
        }
        return null;
    }

    @Override
    public List<Library> getAllLibraries() {
        return libraryList;
    }

    @Override
    public boolean checkOutMediaEntityByIdFromLibraryById(int libraryId, int mediaEntityId, String userId) {
        List<MediaEntity> mediaEntityList = getAllMediaEntitiesByLibraryId(libraryId);
        if (mediaEntityList != null && !mediaEntityList.isEmpty()) {
            for (Object mediaEntity : mediaEntityList) {
                MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;
                int currentId = currentMediaEntity.getId();

                //if the book could be found and is not yet checked out -> check it out
                if (currentId == mediaEntityId && !currentMediaEntity.isCheckedOut()) {
                    currentMediaEntity.setCheckedOut(true);
                    mediaEntityReservations.put(mediaEntityId, userId);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean returnMediaEntityByIdToLibraryById(int libraryId, int mediaEntityId, String userId) {
        List<MediaEntity> mediaEntityList = getAllMediaEntitiesByLibraryId(libraryId);
        if (mediaEntityList != null && !mediaEntityList.isEmpty()) {
            for (Object mediaEntity : mediaEntityList) {
                MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;
                int currentId = currentMediaEntity.getId();

                //if the book could be found and is not yet returned -> return it
                if (currentId == mediaEntityId && currentMediaEntity.isCheckedOut()) {
                    currentMediaEntity.setCheckedOut(false);
                    mediaEntityReservations.remove(mediaEntityId);
                    return true;
                }
            }
        }
        return false;
    }
}
