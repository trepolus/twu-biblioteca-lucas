package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.printWelcomeMsg();
        cli.printMenu();
        int chosenOption = cli.promptUserInputForMenuOption();
        cli.doRequiredMenuAction(chosenOption);
    }
}
