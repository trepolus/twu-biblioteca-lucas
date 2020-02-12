package com.twu.gui;

import com.twu.entities.Book;
import com.twu.entities.MediaEntity;
import com.twu.entities.Movie;

import java.util.List;
import java.util.Scanner;

public class CLI {

    private final Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great movie and book titles in Bangalore!");
        System.out.println();
    }

    public String promptUserInputForMenuOption() {
        return scanner.next();
    }

    public int welcomeBiblioteca(){
        String userResponse = "";
        printWelcomeMsg();

        while (!userResponse.equals("exit")) {
            System.out.println(" ------------------------ BIBLIOTECA----------------------");
            System.out.println("|            Press 1 for: Book Menu                       |");
            System.out.println("|            Press 2 for: Movie Menu                      |");
            System.out.println("|            Type exit for: Leaving the Application       |");
            System.out.println(" ---------------------------------------------------------");

            userResponse = promptUserInputForMenuOption();

            switch (userResponse){
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "exit":
                    printThanksGoodbye();
                    return 0;
                default:
                    printNotAValidOption();
            }
        }
        return 0;
    }

    public void printBookMenu() {
        System.out.println(" ------------------- BOOK MENU ---------------------------");
        System.out.println("|            Press 0 for: List of Books                   |");
        System.out.println("|            Press 1 for: Renting Books                   |");
        System.out.println("|            Press 2 for: Returning Books                 |");
        System.out.println("|            Type exit for: Leaving the Application       |");
        System.out.println(" ---------------------------------------------------------");
    }

    public void printListOfBooks(List<MediaEntity> mediaEntities, boolean checkedOut) {

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

    public void printMovieMenu() {
        System.out.println(" --------------------- MOVIE MENU ------------------------");
        System.out.println("|            Press 0 for: List of Movies                  |");
        System.out.println("|            Type login for: Logging in as a user         |");
        System.out.println("|            Type exit for: Leaving the Application       |");
        System.out.println(" ---------------------------------------------------------");
    }

    public void printMovieMenuOfLoggedInUser (String userLibraryNumber){
        System.out.println(" --------------------- Movie MENU ------------------------");
        System.out.println("|            Press 0 for: List of Movies                  |");
        System.out.println("|            Press 1 for: Renting Movies                  |");
        System.out.println("|            Press 2 for: Returning Movies                |");
        System.out.println("|            Press 3 for: User Information                |");
        System.out.println("|            Type exit for: Leaving the Application       |");
        System.out.println("|                                                         |");
        System.out.println();
        System.out.printf("|            Logged in as user: %s       |", userLibraryNumber);
        System.out.println(" ---------------------------------------------------------");
    }

    public void printListOfMovies(List<MediaEntity> mediaEntities, boolean checkedOut) {

        String longLine = " -------------------------------------------------------------------------------------------------------------------";
        System.out.println(longLine);
        System.out.printf("|%10s |%40s  |%15s  |%30s  |%10s|", "ID", "Name", "Year", "Director", "Rating");
        System.out.println();
        System.out.println(longLine);
        for (Object mediaEntity : mediaEntities) {
            MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;

            if(currentMediaEntity.isCheckedOut() == checkedOut) {
                System.out.printf("|%10s |%40s  |%15s  |%30s  |%10s|", ((MediaEntity) mediaEntity).getId(), ((MediaEntity) mediaEntity).getName(), ((MediaEntity) mediaEntity).getYear(), ((Movie) mediaEntity).getDirector(), ((Movie) mediaEntity).getRating());
                System.out.println();
            }
        }
        System.out.println(longLine);
    }

    public void printRequestForCheckout(String typeOfMediaEntity){
        System.out.printf("Select the id of the %s you want to checkout:", typeOfMediaEntity);
        System.out.println();
    }

    public void printRequestForRental(String typeOfMediaEntity){
        System.out.printf("Select the id of the %s you want to return:", typeOfMediaEntity);
        System.out.println();
    }

    public void printNotAValidOption(){
        System.out.println("Please select a valid option!");
    }

    public void printThanksGoodbye(){
        System.out.println("Thanks for using our service. Good bye!");
    }

    public void printSuccesfulRental(String typeOfMediaEntity){

        System.out.printf("Thank you! Enjoy the %s", typeOfMediaEntity);
        System.out.println();
    }

    public void printUnsuccesfulRental(String typeOfMediaEntity){

        System.out.printf("Sorry, that %s is not available", typeOfMediaEntity);
        System.out.println();
    }

    public void printSuccesfulReturn(String typeOfMediaEntity){

        System.out.printf("Thank you for returning the %s", typeOfMediaEntity);
        System.out.println();
    }

    public void printUnsuccesfulReturn(String typeOfMediaEntity){

        System.out.printf("That is not a valid %s to return", typeOfMediaEntity);
        System.out.println();
    }

}
