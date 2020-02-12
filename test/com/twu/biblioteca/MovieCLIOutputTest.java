package com.twu.biblioteca;

import com.twu.gui.BookCLI;
import com.twu.gui.MovieCLI;
import com.twu.service.LibraryService;
import com.twu.service.LibraryServiceImpl;
import groovy.json.internal.IO;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MovieCLIOutputTest {

    private MovieCLI movieCLI;
    private ByteArrayOutputStream byteArrayOutputStream;
    private LibraryService libraryService;

    @Before
    public void init() {
        this.libraryService = new LibraryServiceImpl();
        libraryService.createAndFillLibraryWithMovies(null);

        this.movieCLI = new MovieCLI(libraryService);
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void shouldPrintWelcomeMsgToCli() throws IOException {
        movieCLI.printWelcomeMsg();
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, is("Welcome to Biblioteca Movies. Your one-stop shop for great movie titles in Bangalore!\n\n"));
    }

    @Test
    public void shouldPrintListOfMediaEntities() throws IOException {
        int libraryId = 1;
        movieCLI.printListOfMediaEntities(libraryId, false);
        byteArrayOutputStream.flush();
        String allWrittenLines = new String(byteArrayOutputStream.toByteArray());
        assertThat(allWrittenLines, CoreMatchers.allOf(
                containsString("Quentin Tarantino"),
                containsString("1994"),
                containsString("2018"),
                containsString("|"),
                containsString("9"),
                containsString("Miloš Forman"),
                containsString("unrated")
        ));
    }
}
