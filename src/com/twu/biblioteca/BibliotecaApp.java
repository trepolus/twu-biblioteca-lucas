package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        MenuService bibliotecaProgram = new MenuServiceImpl();

        bibliotecaProgram.startBiblioteca();
    }
}
