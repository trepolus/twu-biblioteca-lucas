package com.twu.biblioteca;

import com.twu.entities.Book;
import com.twu.entities.MediaEntity;
import com.twu.service.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);
    private LibraryService libraryService;

    public CLI(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    public void printListOfMediaEntities(String libraryName) {

        List mediaEntities = libraryService.getAllMediaEntitiesByLibraryName(libraryName);

        for (Object mediaEntity: mediaEntities){
            System.out.println(mediaEntity);
        }
    }

    public void printMenu() {
        System.out.println("Press 0 for: List of Books");
        System.out.println("Press 1 for: Rent Books");
        System.out.println();

    }

    public void doRequiredMenuAction(String chosenOption) {
        if (chosenOption.equals("0")) {
            printListOfMediaEntities("TW Library");
        }
    }

    public String promptUserInputForMenuOption () {
        String chosenOption = scanner.next();
        return chosenOption;
    }

}
