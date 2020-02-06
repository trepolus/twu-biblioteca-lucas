package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class CLI {

    List<String> bookList = new ArrayList<>();

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!");
    }

    public void printListOfBooks() {

        bookList.add("FirstBook");
        bookList.add("SecondBook");
        bookList.add("ThirdBook");

        for (String book : bookList){
            System.out.println(book);
        }

        //System.out.println("FirstBook\nSecondBook\nThirdBook");
    }
}
