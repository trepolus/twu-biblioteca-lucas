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
            System.out.println("Type exit for: Leaving the Application");
    }

    public boolean doRequiredMenuAction(String chosenOption) {
        switch (chosenOption){
            case "0":
                printListOfMediaEntities("TW Library");
                return true;
            case "1":
                System.out.println("Book rented!");
                return true;
            case "exit":
                System.out.println("Thanks for using our service. Good bye!");
                return false;
            default:
                System.out.println("Please select a valid option!");
                return true;
        }
    }

    public String promptUserInputForMenuOption () {
        String chosenOption = scanner.nextLine();
        return chosenOption;
    }

    public void startMenu(){
        printWelcomeMsg();

        boolean continueToDisplayMenuOptions = true;

        while (continueToDisplayMenuOptions){
            printMenu();
            promptUserInputForMenuOption();
            String userInput = promptUserInputForMenuOption();
            continueToDisplayMenuOptions = doRequiredMenuAction(userInput);
        }
    }

}
