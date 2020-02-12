package com.twu.biblioteca;

import com.twu.gui.CLI;
import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
    public void shouldPrintWelcomeMsgToCli() throws Exception {
        cli.printWelcomeMsg();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, is("Welcome to Biblioteca. Your one-stop shop for great book titles in Bangalore!\n\n"));
    }

    @Test
    public void shouldDisplayBookListOutput() throws Exception {
        //Redirect System.out to buffer
        int libraryName = 1;
        cli.printListOfMediaEntities(libraryName, false);
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
                containsString("Press 1 for: Renting Books")
        ));
    }

    @Test
    public void zeroShouldReturnRequiredMenuAction() throws IOException {

        int zeroShouldReturnZero = cli.doRequiredMenuAction("0");

        assertThat(zeroShouldReturnZero, is(0));

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
    public void oneShouldLeadToCheckoutPrompt() throws IOException {
        int oneShouldReturnOne = cli.doRequiredMenuAction("1");

        assertThat(oneShouldReturnOne, is(1));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Select the id of the book you want to checkout:")
        ));
    }

    @Test
    public void twoShouldLeadToReturnPrompt() throws IOException {
        int twoShouldReturnTwo = cli.doRequiredMenuAction("2");

        assertThat(twoShouldReturnTwo, is(2));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Select the id of the book you want to return:")
        ));
    }

    @Test
    public void shouldTriggerExitMenuAction() throws IOException {
        int exitShouldReturnMinus2 = cli.doRequiredMenuAction("exit");

        assertThat(exitShouldReturnMinus2, is(-2));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Thanks for using our service. Good bye!")
        ));
    }

    @Test
    public void wrongInputShouldTriggerWarning() throws IOException {
        int wrongInputShouldReturnMinusOne = cli.doRequiredMenuAction("349f883eOIWRJNVWPOKSN@");

        assertThat(wrongInputShouldReturnMinusOne, is(-1));

        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Please select a valid option!")
        ));
    }

    @Test
    public void shouldDisplayMsgForCheckoutAndReturnBook() {
        cli.checkoutBook("1");
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Thank you! Enjoy the book")
        ));
        cli.checkoutBook("1");
        allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Sorry, that book is not available")
        ));

        cli.returnBook("trft78yg");
        allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("This is not a valid option")
        ));

        cli.returnBook("1");
        allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Thank you for returning the book")
        ));

        cli.returnBook("1");
        allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("That is not a valid book to return")
        ));

        cli.returnBook("87239hfui3");
        allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("This is not a valid option")
        ));
    }

    @Test
    public void shouldPrintListOfMediaEntities() {
        cli.printListOfMediaEntities(1,false);

        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                        containsString("Refactoring"),
                        containsString("Unicorn Gorilla")
                ));

        //checkout Refactoring Book, should not be displayed now
        libraryService.checkOutMediaEntityByIdFromLibraryById(1,1);

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream2));
        cli.printListOfMediaEntities(1,false);
        allWrittenLines = new String(byteArrayOutputStream2.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.not(
                containsString("Refactoring")
        ));

        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream3));
        cli.printListOfMediaEntities(1,true);
        allWrittenLines = new String(byteArrayOutputStream3.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.containsString("Refactoring")
        );
    }
}
