package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CLIOutputTest {

    private CLI cli = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;

    @Before
    public void init() {
        this.cli = new CLI();
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

    }

    @Test
    public void shouldPrintWelcomeMsgToCli() throws Exception{
        cli.printWelcomeMsg();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, is("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n"));
    }

    @Test
    public void shouldDisplayBookListOutput() throws Exception{
        //Redirect System.out to buffer
        cli.printListOfBooks();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Life is good | Unicorn Gorilla | 2019"),
                containsString("Fowler"),
                containsString("2026")
        ));
    }

    @Test
    public void shouldDisplayMenuAfterWelcomeMsg() throws IOException {
        cli.printMenu();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Press 0 for: List of Books"),
                containsString("Press 1 for: Rent Book")
        ));
    }

    @Test
    public void shouldReturnRequiredMenuAction() throws IOException {
        cli.doRequiredMenuAction(0);
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Life is good | Unicorn Gorilla | 2019"),
                containsString("Fowler"),
                containsString("2026")
        ));
    }
}