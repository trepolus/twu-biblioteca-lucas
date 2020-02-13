package com.twu.biblioteca;

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

public class MovieCLIOutputTest {

    private CLI cli;
    private ByteArrayOutputStream byteArrayOutputStream;
    private LibraryService libraryService;

    @Before
    public void init() {
        this.libraryService = new LibraryServiceImpl();
        libraryService.createAndFillLibraryWithMovies(null);

        this.cli = new CLI();
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Test
    public void shouldPrintListOfMediaEntities() throws IOException {
        cli.printListOfMovies(libraryService.getAllMediaEntitiesByLibraryId(1), false);
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
