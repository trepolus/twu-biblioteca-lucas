package com.twu.biblioteca;

import com.twu.gui.MenuService;
import com.twu.gui.MenuServiceImpl;

public class BibliotecaApp {

    public static void main(String[] args) {
        MenuService bibliotecaProgram = new MenuServiceImpl();

        bibliotecaProgram.startBiblioteca();
    }
}
