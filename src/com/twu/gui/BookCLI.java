package com.twu.gui;

import com.twu.entities.Book;
import com.twu.entities.MediaEntity;
import com.twu.service.LibraryService;

import java.util.List;

public class BookCLI extends CLI{

    public BookCLI(LibraryService libraryService) {
        super(libraryService);
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
        System.out.println();
    }

    public void printMenu() {
        System.out.println(" ------------------------ MENU ---------------------------");
        System.out.println("|            Press 0 for: List of Books                   |");
        System.out.println("|            Press 1 for: Renting Books                   |");
        System.out.println("|            Press 2 for: Returning Books                 |");
        System.out.println("|            Type exit for: Leaving the Application       |");
        System.out.println(" ---------------------------------------------------------");
    }

    public void printListOfMediaEntities(int libraryId, boolean checkedOut) {

        List<MediaEntity> mediaEntities = libraryService.getAllMediaEntitiesByLibraryId(libraryId);
        System.out.println(" -----------------------------------------------------------------------------------");
        System.out.printf("|%10s |%30s  |%20s  |%15s|", "ID", "Title", "Author", "Year");
        System.out.println();
        System.out.println(" -----------------------------------------------------------------------------------");
        for (Object mediaEntity : mediaEntities) {
            MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;

            if(currentMediaEntity.isCheckedOut() == checkedOut) {
                System.out.printf("|%10s |%30s  |%20s  |%15s|", ((MediaEntity) mediaEntity).getId(), ((MediaEntity) mediaEntity).getName(), ((Book) mediaEntity).getAuthor(), ((MediaEntity) mediaEntity).getYear());
                System.out.println();
            }
        }
        System.out.println(" -----------------------------------------------------------------------------------");
    }

    public void startMenu() {
        printWelcomeMsg();

        int userDecision = 0;

        while (userDecision > -2) {
            printMenu();
            String userInput = promptUserInputForMenuOption();
            userDecision = doRequiredMenuAction(userInput);

            if(userDecision == 1){
                userInput = promptUserInputForMenuOption();
                checkoutMediaEntity(userInput);
            }
            else if (userDecision == 2){
                userInput = promptUserInputForMenuOption();
                returnMediaEntity(userInput);
            }
        }
    }

    public int doRequiredMenuAction(String chosenOption) {
        switch (chosenOption) {
            case "0":
                printListOfMediaEntities(1, false);
                System.out.println();
                return 0;
            case "1":
                printListOfMediaEntities(1, false);
                System.out.println("Select the id of the book you want to checkout:");
                return 1;
            case "2":
                printListOfMediaEntities(1, true);
                System.out.println("Select the id of the book you want to return:");
                return 2;
            case "exit":
                System.out.println("Thanks for using our service. Good bye!");
                return -2;
            default:
                System.out.println("Please select a valid option!");
                System.out.println();
                return -1;
        }
    }

    public void checkoutMediaEntity(String userInput){
        try {
            int selectedOption = Integer.parseInt(userInput);
            boolean checkOutSuccessful = libraryService.checkOutMediaEntityByIdFromLibraryById(1, selectedOption);

            if (checkOutSuccessful) {
                System.out.println("Thank you! Enjoy the book");
            } else {
                System.out.println("Sorry, that book is not available");
            }
        }
        catch (NumberFormatException ex){
            System.out.println("This is not a valid option");
        }
    }

    public void returnMediaEntity(String userInput){
        try {
            int selectedOption = Integer.parseInt(userInput);
            boolean checkOutSuccessful = libraryService.returnMediaEntityByIdToLibraryById(1, selectedOption);

            if (checkOutSuccessful) {
                System.out.println("Thank you for returning the book");
            } else {
                System.out.println("That is not a valid book to return");
            }
        }
        catch (NumberFormatException ex){
            System.out.println("This is not a valid option");
        }
    }
}
