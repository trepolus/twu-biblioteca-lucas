package com.twu.biblioteca;

import com.twu.entities.MediaEntity;
import com.twu.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class CLI {

    private LibraryService libraryService;
    private final Scanner scanner;

    public CLI(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
        System.out.println();
    }

    public void printListOfMediaEntities(String libraryName) {

        List<MediaEntity> mediaEntities = libraryService.getAllMediaEntitiesByLibraryName(libraryName);
        System.out.println(" ------------------------------------------------------------------");
        System.out.printf("|%25s  |%20s  |%15s|", "Title", "Author", "Year");
        System.out.println();
        System.out.println(" ------------------------------------------------------------------");
        for (Object mediaEntity : mediaEntities) {
            System.out.printf("|%25s  |%20s  |%15s|", ((MediaEntity) mediaEntity).getName(), ((MediaEntity) mediaEntity).getAuthor(), ((MediaEntity) mediaEntity).getYear());
            System.out.println();
        }
        System.out.println(" ------------------------------------------------------------------");
    }

    public void printMenu() {
        System.out.println(" ------------------------ MENU ---------------------------");
        System.out.println("|            Press 0 for: List of Books                   |");
        System.out.println("|            Press 1 for: Rent Books                      |");
        System.out.println("|            Type exit for: Leaving the Application       |");
        System.out.println(" ---------------------------------------------------------");
    }

    public boolean doRequiredMenuAction(String chosenOption) {
        switch (chosenOption) {
            case "0":
                printListOfMediaEntities("TW Library");
                System.out.println();
                return true;
            case "1":
                System.out.println("Book rented!");
                System.out.println();
                return true;
            case "exit":
                System.out.println("Thanks for using our service. Good bye!");
                return false;
            default:
                System.out.println("Please select a valid option!");
                System.out.println();
                return true;
        }
    }

    public String promptUserInputForMenuOption() {
        return scanner.next();
    }

    public void startMenu() {
        printWelcomeMsg();

        boolean continueToDisplayMenuOptions = true;

        while (continueToDisplayMenuOptions) {
            printMenu();
            String userInput = promptUserInputForMenuOption();
            continueToDisplayMenuOptions = doRequiredMenuAction(userInput);
        }
    }

}
