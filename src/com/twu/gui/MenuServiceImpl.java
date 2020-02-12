package com.twu.gui;

import com.twu.entities.MediaEntity;
import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    private CLI cli;
    private LibraryService bookLibraryService;
    private LibraryService movieLibraryService;
    private final String bookMediaEntityName = "book";
    private final String movieMediaEntityName = "movie";


    public MenuServiceImpl() {
        //initialize Biblioteca Books
        this.bookLibraryService = new LibraryServiceImpl();
        this.bookLibraryService.createAndFillLibraryWithBooks(null);

        //initialize Biblioteca Movies
        this.movieLibraryService = new LibraryServiceImpl();
        this.movieLibraryService.createAndFillLibraryWithMovies(null);

        //initialize CLI
        this.cli = new CLI();
    }

    @Override
    public void startBiblioteca() {

        int whichProgramShouldIStart = cli.welcomeBiblioteca();

        if (whichProgramShouldIStart == 1){
            startBookMenu();
        }
        else if (whichProgramShouldIStart == 2){
            startMovieMenu();
        }
    }

    private void startBookMenu() {
        int userDecision = 0;

        while (userDecision > -2) {
            cli.printBookMenu();
            String userInput = cli.promptUserInputForMenuOption();
            userDecision = doRequiredBookMenuAction(userInput);

            if (userDecision == 1) {
                userInput = cli.promptUserInputForMenuOption();
                checkoutMediaEntity(userInput, bookLibraryService, bookMediaEntityName);
            } else if (userDecision == 2) {
                userInput = cli.promptUserInputForMenuOption();
                returnMediaEntity(userInput, bookLibraryService, bookMediaEntityName);
            }
        }
    }

    public int doRequiredBookMenuAction(String chosenOption) {
        List<MediaEntity> listOfBooks = bookLibraryService.getAllMediaEntitiesByLibraryId(1);

        switch (chosenOption) {
            case "0":
                cli.printListOfBooks(listOfBooks, false);
                System.out.println();
                return 0;
            case "1":
                cli.printListOfBooks(listOfBooks, false);
                cli.printRequestForCheckout(bookMediaEntityName);
                return 1;
            case "2":
                cli.printListOfBooks(listOfBooks, true);
                cli.printRequestForRental(bookMediaEntityName);
                return 2;
            case "exit":
                cli.printThanksGoodbye();
                return -2;
            default:
                cli.printNotAValidOption();
                System.out.println();
                return -1;
        }
    }

    public void checkoutMediaEntity(String userInput, LibraryService libraryService, String mediaEntityType){
        try {
            int selectedOption = Integer.parseInt(userInput);
            boolean checkOutSuccessful = libraryService.checkOutMediaEntityByIdFromLibraryById(1, selectedOption);

            if (checkOutSuccessful) {
                cli.printSuccesfulRental(mediaEntityType);
            } else {
                cli.printUnsuccesfulRental(mediaEntityType);
            }
        }
        catch (NumberFormatException ex){
            cli.printNotAValidOption();
        }
    }

    public void returnMediaEntity(String userInput, LibraryService libraryService, String mediaEntityType){
        try {
            int selectedOption = Integer.parseInt(userInput);
            boolean checkOutSuccessful = libraryService.returnMediaEntityByIdToLibraryById(1, selectedOption);

            if (checkOutSuccessful) {
                cli.printSuccesfulReturn(mediaEntityType);
            } else {
                cli.printUnsuccesfulReturn(mediaEntityType);
            }
        }
        catch (NumberFormatException ex){
            cli.printNotAValidOption();
        }
    }

    private void startMovieMenu(){
        String userDecision = "";

        while (!userDecision.equals("exit")) {
            cli.printMovieMenu();
            userDecision = cli.promptUserInputForMenuOption();

            switch (userDecision){
                case "0":
                    //print all available movies
                    cli.printListOfMovies(movieLibraryService.getAllMediaEntitiesByLibraryId(1), false);
                    break;
                case "login":
                    System.out.println("Login functionality not yet implemented :(");
                    break;
                case "exit":
                    cli.printThanksGoodbye();
                    break;
                default:
                    cli.printNotAValidOption();
            }
        }
    }
}
