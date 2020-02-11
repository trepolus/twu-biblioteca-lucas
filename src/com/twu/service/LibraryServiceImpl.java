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
    public Library getLibraryByName(String name) {
        if (!libraryList.isEmpty()){
            for (Library library : libraryList){
                if(library.getName().equals(name)){
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
        if (!libraryList.isEmpty()){
            for (Library library : libraryList){
                if(library.getId() == id){
                    return library;
                }
            }
        }
        return null;
    }

    @Override
    public List<MediaEntity> getAllMediaEntitiesByLibraryId(int id) {
        Library library = getLibraryById(1);
        if (library != null){
            return library.getMediaEntityList();
        }
        return null;
    }

    @Override
    public List<Library> getAllLibraries() {
        return libraryList;
    }

    @Override
    public boolean checkOutMediaEntityByIdFromLibraryById(int libraryId, int mediaEntityId) {
        List<MediaEntity> mediaEntityList = getAllMediaEntitiesByLibraryId(libraryId);
        if (mediaEntityList != null && !mediaEntityList.isEmpty()){
            for (Object mediaEntity : mediaEntityList){
                MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;
                int currentId = currentMediaEntity.getId();

                //if the book could be found and is not yet checked out -> check it out
                if (currentId == mediaEntityId && !currentMediaEntity.isCheckedOut()){
                    currentMediaEntity.setCheckedOut(true);
                    return true;
                }
            }
        }
        return false;
    }
}
