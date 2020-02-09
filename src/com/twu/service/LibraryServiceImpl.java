package com.twu.service;

import com.twu.entities.Library;
import com.twu.entities.Book;
import com.twu.entities.MediaEntity;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService{

    private List<Library> libraryList;

    public LibraryServiceImpl() {
        libraryList = new ArrayList<>();
    }

    @Override
    public Library createAndFillLibraryWithBooks(Library library) {

        if (library == null){
            // create new books and add them to a new library
            ArrayList<MediaEntity> bookList = new ArrayList<>();

            Book book1 = new Book("Refactoring", "Martin Fowler", 1998);
            Book book2 = new Book("Life is good", "Unicorn Gorilla", 2019);
            Book book3 = new Book("Design Patterns for Noobs", "Lucas Kummer", 2026);

            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);

            Library initialLibrary = new Library(bookList, "TW Library");

            library = initialLibrary;
        }
        libraryList.add(library);
        return library;
    }

    @Override
    public Library getLibraryByName(String name) {
        if (!libraryList.isEmpty()){
            for (Library library : libraryList){
                if(library.getName() == name){
                    return library;
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaEntity> getAllMediaEntitiesByLibraryName(String name) {
        Library library = getLibraryByName(name);
        return library.getMediaEntityList();
    }

    @Override
    public List<Library> getAllLibraries() {
        return libraryList;
    }
}
