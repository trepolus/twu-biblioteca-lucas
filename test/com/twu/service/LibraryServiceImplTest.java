package com.twu.service;

import com.twu.entities.Book;
import com.twu.entities.Library;
import com.twu.entities.MediaEntity;
import com.twu.entities.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class LibraryServiceImplTest {

    private LibraryService libraryService;

    @Before
    public void init() {
        this.libraryService = new LibraryServiceImpl();
    }

    @Test
    public void shouldCreateAndFillLibraryWithBooksAndReturnIt() {
        Library libraryToTest = libraryService.createAndFillLibraryWithBooks(null);

        ArrayList<MediaEntity> mockBookList = new ArrayList<>();

        Book book1 = new Book("Refactoring", "Martin Fowler", 1998, 1);
        Book book2 = new Book("Life is good", "Unicorn Gorilla", 2019, 2);
        Book book3 = new Book("Design Patterns for Noobs", "Lucas Kummer", 2026, 3);

        mockBookList.add(book1);
        mockBookList.add(book2);
        mockBookList.add(book3);

        Library mockLibrary = new Library(mockBookList, "TW Library", 1);

        assertThat(libraryToTest.getName(), is(mockLibrary.getName()));

        String nameOfTestBook2 = libraryToTest.getMediaEntityList().get(1).getName();

        assertThat(nameOfTestBook2, is(book2.getName()));

    }

    @Test
    public void shouldCreateAndFillLibraryWithMoviesAndReturnIt() {
        Library libraryToTest = libraryService.createAndFillLibraryWithMovies(null);

        ArrayList<MediaEntity> mockMovieList = new ArrayList<>();

        Movie movie1 = new Movie(1,"Pulp Fiction", 1994, "Quentin Tarantino", 10);
        Movie movie2 = new Movie(2,"Inglorious Basterds", 2009, "Quentin Tarantino", 9);
        Movie movie3 = new Movie(3,"Sharknado 6", 2018, "Anthony Ferrante", 2);

        mockMovieList.add(movie1);
        mockMovieList.add(movie2);
        mockMovieList.add(movie3);

        Library mockLibrary = new Library(mockMovieList, "Movie Library", 1);

        assertThat(libraryToTest.getName(), is(mockLibrary.getName()));

        String nameOfTestMovie2 = libraryToTest.getMediaEntityList().get(1).getName();

        assertThat(nameOfTestMovie2, is(movie2.getName()));

    }

    @Before
    public void initializeSomeLibraries() {
        Library library1 = new Library("Fantastic Library", 2);
        Library library2 = new Library("Old Library", 3);

        libraryService.createAndFillLibraryWithBooks(null);
        libraryService.createAndFillLibraryWithBooks(library1);
        libraryService.createAndFillLibraryWithBooks(library2);
    }


    @Test
    public void shouldReturnLibraryByName() {
        assertThat(libraryService.getLibraryByName("Fantastic Library").getName(), is("Fantastic Library"));
        assertThat(libraryService.getLibraryByName("Old Library").getName(), is("Old Library"));
        assertThat(libraryService.getLibraryByName("Cool Library"), is(nullValue()));
    }

    @Test
    public void getAllMediaEntitiesByLibraryName() {
        List<MediaEntity> allMediaEntriesOfTWLibrary = libraryService.getAllMediaEntitiesByLibraryName("TW Library");

        MediaEntity mediaEntity1 = allMediaEntriesOfTWLibrary.get(0);
        assertThat(mediaEntity1.getName(), is("Refactoring"));
        assertThat(allMediaEntriesOfTWLibrary.size(), is(3));

        List<MediaEntity> allMediaEntriesOfOldLibrary = libraryService.getAllMediaEntitiesByLibraryName("Old Library");
        assertThat(allMediaEntriesOfOldLibrary.isEmpty(), is(true));

    }

    @Test
    public void shouldReturnLibraryById() {
        assertThat(libraryService.getLibraryById(2).getName(), is("Fantastic Library"));
        assertThat(libraryService.getLibraryById(3).getName(), is("Old Library"));
        assertThat(libraryService.getLibraryById(5), is(nullValue()));
    }

    @Test
    public void getAllMediaEntitiesByLibraryId() {
        List<MediaEntity> allMediaEntriesOfTWLibrary = libraryService.getAllMediaEntitiesByLibraryId(1);

        MediaEntity mediaEntity1 = allMediaEntriesOfTWLibrary.get(0);
        assertThat(mediaEntity1.getName(), is("Refactoring"));
        assertThat(allMediaEntriesOfTWLibrary.size(), is(3));

        List<MediaEntity> allMediaEntriesOfOldLibrary = libraryService.getAllMediaEntitiesByLibraryId(3);
        assertThat(allMediaEntriesOfOldLibrary.isEmpty(), is(true));

    }

    @Test
    public void shouldReturnAllLibraries() {
        List<Library> allLibraries = libraryService.getAllLibraries();

        assertThat(allLibraries.size(), is(3));

        Library fantasticLibrary = allLibraries.get(1);
        assertThat(fantasticLibrary.getName(), is("Fantastic Library"));
    }

    @Test
    public void shouldCheckoutMediaEntity() {
        boolean checkoutMediaEntity = libraryService.checkOutMediaEntityByIdFromLibraryById(1, 1, "noUser");

        //should set checkout value to true, entity should now be checked out
        assertThat(checkoutMediaEntity, is(true));

        checkoutMediaEntity = libraryService.checkOutMediaEntityByIdFromLibraryById(1, 1, "noUser");
        //entity should already be checked out
        assertThat(checkoutMediaEntity, is(false));
    }

    @Test
    public void shouldReturnMediaEntity() {
        //first checkout book
        libraryService.checkOutMediaEntityByIdFromLibraryById(1, 1, "noUser");

        //then return it
        boolean returnMediaEntity = libraryService.returnMediaEntityByIdToLibraryById(1, 1, "noUser");

        //should set checkout value to false, returnMediaEntity should therefore return true
        assertThat(returnMediaEntity, is(true));

        returnMediaEntity = libraryService.returnMediaEntityByIdToLibraryById(1, 1, "noUser");
        //entity should already be returned, method should return false
        assertThat(returnMediaEntity, is(false));
    }
}