package com.twu.gui;

import com.twu.entities.Book;
import com.twu.entities.MediaEntity;
import com.twu.entities.Movie;
import com.twu.service.LibraryService;

import java.util.List;

public class MovieCLI extends CLI {
    public MovieCLI(LibraryService libraryService) {
        super(libraryService);
    }

    @Override
    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca Movies. Your one-stop shop for great movie titles in Bangalore!\n");
    }

    @Override
    public void printListOfMediaEntities(int libraryId, boolean checkedOut) {

        List<MediaEntity> mediaEntities = libraryService.getAllMediaEntitiesByLibraryId(libraryId);
        String longLine = " ----------------------------------------------------------------------------------------------------------------";
        System.out.println(longLine);
        System.out.printf("|%10s |%40s  |%15s  |%30s  |%10s|", "ID", "Name", "Year", "Director", "Movie Rating");
        System.out.println();
        System.out.println(longLine);
        for (Object mediaEntity : mediaEntities) {
            MediaEntity currentMediaEntity = (MediaEntity) mediaEntity;

            if(currentMediaEntity.isCheckedOut() == checkedOut) {
                System.out.printf("|%10s |%40s  |%15s  |%30s  |%10s|", ((MediaEntity) mediaEntity).getId(), ((MediaEntity) mediaEntity).getName(), ((MediaEntity) mediaEntity).getYear(), ((Movie) mediaEntity).getDirector(), ((Movie) mediaEntity).getRating());
                System.out.println();
            }
        }
        System.out.println(longLine);
    }
}
