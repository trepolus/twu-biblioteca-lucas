package com.twu.biblioteca;

import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CLIOutputTest {

    private CLI cli;
    private ByteArrayOutputStream byteArrayOutputStream;
    private LibraryService libraryService;

    @Before
    public void init() {
        this.libraryService = new LibraryServiceImpl();
        libraryService.createAndFillLibraryWithBooks(null);

        this.cli = new CLI(libraryService);
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

    }

    @Test
    public void shouldPrintWelcomeMsgToCli() throws Exception{
        cli.printWelcomeMsg();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, is("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n\n"));
    }

    @Test
    public void shouldDisplayBookListOutput() throws Exception{
        //Redirect System.out to buffer
        String libraryName = "TW Library";
        cli.printListOfMediaEntities(libraryName);
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Life is good"),
                containsString("Unicorn Gorilla"),
                containsString("2019"),
                containsString("|"),
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
    public void zeroShouldReturnRequiredMenuAction() throws IOException {

        boolean zeroShouldReturnTrue = cli.doRequiredMenuAction("0");

        assertThat(zeroShouldReturnTrue, is(true));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Life is good"),
                containsString("Unicorn Gorilla"),
                containsString("2019"),
                containsString("|"),
                containsString("Fowler"),
                containsString("2026")
        ));
    }

    @Test
    public void shouldTriggerExitMenuAction() throws IOException {
        boolean zeroShouldReturnTrue = cli.doRequiredMenuAction("exit");

        assertThat(zeroShouldReturnTrue, is(false));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Thanks for using our service. Good bye!")
        ));
    }

    @Test
    public void wrongInputShouldTriggerWarning() throws IOException {
        boolean zeroShouldReturnTrue = cli.doRequiredMenuAction("349f883eOIWRJNVWPOKSN@");

        assertThat(zeroShouldReturnTrue, is(true));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Please select a valid option!")
        ));
    }
}
