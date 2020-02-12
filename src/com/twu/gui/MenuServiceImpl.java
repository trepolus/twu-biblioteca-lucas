package com.twu.service;

import com.twu.biblioteca.CLI;

public class MenuServiceImpl implements MenuService {

    private CLI bookCLI;
    private CLI movieCLI;
    private LibraryService bookLibraryService;
    private LibraryService movieLibraryService;

    public MenuServiceImpl() {
        //initialize Biblioteca Books
        this.bookLibraryService = new LibraryServiceImpl();
        this.bookLibraryService.createAndFillLibraryWithBooks(null);
        this.bookCLI = new CLI(bookLibraryService);

        //initialize Biblioteca Movies
        this.movieLibraryService = new LibraryServiceImpl();
        this.movieCLI = new CLI(movieLibraryService);
    }

    @Override
    public void startBiblioteca() {

    }
}
