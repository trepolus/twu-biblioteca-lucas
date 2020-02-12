package com.twu.gui;

import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;

public class MenuServiceImpl implements MenuService {

    private BookCLI bookCLI;
    private BookCLI movieBookCLI;
    private LibraryService bookLibraryService;
    private LibraryService movieLibraryService;

    public MenuServiceImpl() {
        //initialize Biblioteca Books
        this.bookLibraryService = new LibraryServiceImpl();
        this.bookLibraryService.createAndFillLibraryWithBooks(null);
        this.bookCLI = new BookCLI(bookLibraryService);

        //initialize Biblioteca Movies
        this.movieLibraryService = new LibraryServiceImpl();
        this.movieBookCLI = new BookCLI(movieLibraryService);
    }

    @Override
    public void startBiblioteca() {

    }
}
