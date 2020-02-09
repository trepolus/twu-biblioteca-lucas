package com.twu.biblioteca;

import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;

public class BibliotecaApp {

    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImpl();
        libraryService.createAndFillLibraryWithBooks(null);

        CLI cli = new CLI(libraryService);
        cli.printWelcomeMsg();
        cli.printMenu();
        String chosenOption = cli.promptUserInputForMenuOption();
        cli.doRequiredMenuAction(chosenOption);
    }
}
