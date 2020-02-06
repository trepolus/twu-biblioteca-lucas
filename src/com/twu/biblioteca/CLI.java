package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class CLI {

    List<Book> bookList = new ArrayList<>();

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    public void printListOfBooks() {

        Book book1 = new Book("Refactoring", "Martin Fowler", 1998);
        Book book2 = new Book("Life is good", "Unicorn Gorilla", 2019);
        Book book3 = new Book("Design Patterns for Noobs", "Lucas Kummer", 2026);

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);

        for (Book book : bookList){
            System.out.println(book);
        }
    }
}
