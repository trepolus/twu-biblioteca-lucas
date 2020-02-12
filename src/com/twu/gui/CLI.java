package com.twu.gui;

import com.twu.service.LibraryService;

import java.util.Scanner;

public abstract class CLI {

    private final Scanner scanner;
    protected LibraryService libraryService;

    public CLI(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great Media Products in Bangalore!");
        System.out.println();
    }

    public void printMenu() {}

    public void printListOfMediaEntities(int libraryId, boolean checkedOut) {}

    public String promptUserInputForMenuOption() {
        return scanner.next();
    }

    public void checkoutMediaEntity(String userInput) {}

    public void returnMediaEntity(String userInput) {}
}
