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

/**
 * Created by MTX on 2019-02-24.
 */

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookTest {

    private User user = new User();
    private Book testBook = new Book("Title", "Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN", null);


    @Test
    public void getTitleTest(){
        assertEquals(testBook.getTitle(),"Title");
    }

    @Test
    public void setTitleTest(){
        String newTitle = "Test Title";
        testBook.setTitle(newTitle);
        assertEquals(testBook.getTitle(),newTitle);
    }

    @Test
    public void getAuthorTest(){
        assertEquals(testBook.getAuthor(), "Author");
    }

    @Test
    public void setAuthorTest(){
        String newAuthor = "Test Author";
        testBook.setAuthor(newAuthor);
        assertEquals(testBook.getAuthor(),newAuthor);
    }

    @Test
    public void getISBNTest(){
        assertEquals(testBook.getISBN(),1234567890);
    }

    @Test
    public void setISBNTest(){
        int newISBN = 1;
        testBook.setISBN(newISBN);
        assertEquals(testBook.getISBN(),newISBN);
    }

    @Test
    public void getOwnerTest(){
        assertEquals(testBook.getOwner(),user);
    }

    @Test
    public void setOwnerTest(){
        User newOwner = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");
        testBook.setOwner(newOwner);
        assertEquals(testBook.getOwner(),newOwner);
    }

    @Test
    public void getStatusTest(){
        assertEquals(testBook.getStatus(),BookStatus.AVAILABLE);
    }

    @Test
    public void setStatusTest(){
        testBook.setStatus(BookStatus.BORROWED);
        assertEquals(testBook.getStatus(),BookStatus.BORROWED);
    }

    @Test
    public void getDescriptionTest(){
        assertEquals(testBook.getDescription(), "Description");
    }

    @Test
    public void setDescriptionTest(){
        String newDescription = "Test Description";
        testBook.setDescription(newDescription);
        assertEquals(testBook.getDescription(), newDescription);
    }

    @Test
    public void getSSNTest(){
        assertEquals(testBook.getSearchString(),"SSN");
    }

    @Test
    public void setSSNTest(){
        String newSSN = "Test SSN";
        testBook.setSearchString(newSSN);
        assertEquals(testBook.getSearchString(),newSSN);
    }

}
