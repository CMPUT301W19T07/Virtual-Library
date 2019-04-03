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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Checks to see if the data meets the requirements of a book type
 *
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
 */

public class BookValidator {

    private Book validateBook = null;

    public BookValidator(Book book){
        this.validateBook = book;
    }

    public BookValidator(){

    }
    /**
     * Ensures title is valid (Only contains characters a-z,A-Z,0-9)
     */
    public boolean isValidTitle(String title){
        String regex = "[a-zA-z0-9\\s]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }
    /**
     * Ensures author is valid (Only contains characters a-z,A-Z)
     */
    public boolean isValidAuthor(String author){
        String regex = "[a-zA-z\\s]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(author);
        return matcher.matches();
    }

    public boolean isValidOwner(ArrayList<User> users){
        if(this.validateBook == null){
            throw new IllegalArgumentException("Wrong Usage.");
        }else {
            return users.contains(validateBook.getOwner());
        }
    }
    /**
     * Ensures Owner is valid (User's profile contains the book)
     */
    public boolean isValidOwner(ArrayList<User> users, Book book){
        return users.contains(book.getOwner());
    }

    /**
     * Ensures ISBN is valid (Must contains 10 or 13 digits)
     */
    public boolean isValidISBN(String ISBN){
        String regex = "\\d{10}|\\d{13}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.matches();
    }

    public boolean isValidStatus(){
        if(this.validateBook == null){
            throw new IllegalArgumentException("Wrong Usage.");
        }else {
            return this.validateBook.getStatus().equals(BookStatus.AVAILABLE);
        }
    }
    /**
     * Checks if the book is available
     */
    public boolean isValidStatus(Book book){
        return book.getStatus().equals(BookStatus.AVAILABLE);
    }


    public boolean isMatchSSN(String givenSSN){
        if(this.validateBook == null){
            throw new IllegalArgumentException("Wrong Usage.");
        }else {
            return validateBook.getSearchString().contains(givenSSN);
        }
    }
    /**
     * Checks to see if the search term matches the book
     */
    public boolean isMatchSSN(String givenSSN, Book book){
        return book.getSearchString().contains(givenSSN);
    }

    public boolean isOwned(User owner){
        if(this.validateBook == null){
            throw new IllegalArgumentException("Wrong Usage.");
        }else {
            return this.validateBook.getOwner().equals(owner);
        }
    }
    /**
     * Checks to see if a given book is in the user's possession
     */
    public boolean isOwned(User owner, Book book){
        return book.getOwner().equals(owner);
    }

}
