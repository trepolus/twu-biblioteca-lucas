package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private List<Book> bookList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public CLI() {
        Book book1 = new Book("Refactoring", "Martin Fowler", 1998);
        Book book2 = new Book("Life is good", "Unicorn Gorilla", 2019);
        Book book3 = new Book("Design Patterns for Noobs", "Lucas Kummer", 2026);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    public void printListOfBooks() {

        for (Book book : bookList){
            System.out.println(book);
        }
    }

    public void printMenu() {
        System.out.println("Press 0 for: List of Books");
        System.out.println("Press 1 for: Rent Books");
        System.out.println();

    }

    public void doRequiredMenuAction(int chosenOption) {
        if (chosenOption == 0) {
            printListOfBooks();
        }
    }

    public int promptUserInputForMenuOption () {
        int chosenOption = scanner.nextInt();
        return chosenOption;
    }

}
