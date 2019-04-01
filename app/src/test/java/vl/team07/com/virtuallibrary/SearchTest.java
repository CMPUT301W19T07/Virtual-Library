/*
 * Class Name
 *
 * Date of Initiation
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */
package vl.team07.com.virtuallibrary;

import org.junit.Test;
import static org.junit.Assert.*;

public class SearchTest {

    private User user = new User("Username", "Name","Email@test.com");
    private Book testBook = new Book("Title", "Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN");
    private Search testSearch = new Search();
    private BookList testBookList = new BookList();

    @Test
    public void getSearch(){
        String newSearch = testSearch.getSearch();
        assertEquals(testSearch.getSearch(), newSearch);
    }

    @Test
    public void setSearch(){
        String newSearch = "Test Search";
        testSearch.setSearch(newSearch);
        assertEquals(testSearch.getSearch(),newSearch);
    }

    @Test
    public void hasBook(){
        testBookList.addBook(testBook);
        assertTrue(testBookList.hasBook(testBook));
    }

}
