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

/**
 * Created by MTX on 2019-02-24.
 */


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookValidatorTest {

    private User user = new User("Username","Name","Email@test.com");
    private Book testBook = new Book("Title", "Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN");
    private BookValidator bookValidator = new BookValidator();
    private ArrayList<User> users = new ArrayList<>();

    @Test
    public void isValidTitle(){
        boolean validTitle = bookValidator.isValidTitle("Test Title");
        assertTrue(validTitle);
        boolean notValidTitle = bookValidator.isValidTitle("*&^#!(@123");
        assertFalse(notValidTitle);
    }

    @Test
    public void isValidAuthor(){
        boolean validAuthor = bookValidator.isValidAuthor("Test Author");
        assertTrue(validAuthor);
        boolean notValidAuthor = bookValidator.isValidAuthor("!@%#&)!*$@");
        assertFalse(notValidAuthor);
    }

    @Test
    public void isValidISBN(){
        boolean validISBN_10 = bookValidator.isValidISBN("1234567890");
        assertTrue(validISBN_10);
        boolean validISBN_13 = bookValidator.isValidISBN("1234567890123");
        assertTrue(validISBN_13);
        boolean notValidISBN = bookValidator.isValidISBN("1");
        assertFalse(notValidISBN);
    }

    @Test
    public void isValidOwner(){
        users.add(user);
        boolean validOwner = bookValidator.isValidOwner(users,testBook);
        assertTrue(validOwner);
    }

    @Test
    public void isValidStatus(){
        boolean validStatus = bookValidator.isValidStatus(testBook);
        assertTrue(validStatus);
    }

    @Test
    public void isMatchSSN(){
        boolean validSSN = bookValidator.isMatchSSN("SSN",testBook);
        assertTrue(validSSN);
    }

    @Test
    public void isOwned(){
        boolean validOwner = bookValidator.isOwned(user,testBook);
        assertTrue(validOwner);

        User newOwner = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");
        boolean notOwner = bookValidator.isOwned(newOwner,testBook);
        assertFalse(notOwner);
    }

}
