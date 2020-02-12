package com.twu.biblioteca;

import com.twu.gui.BookCLI;
import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;

public class BibliotecaApp {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImpl();
        libraryService.createAndFillLibraryWithBooks(null);

        BookCLI bookCli = new BookCLI(libraryService);
        bookCli.startMenu();
    }
}
