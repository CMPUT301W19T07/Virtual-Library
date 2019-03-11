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
 * Created by MTX on 2019-02-24.
 */

public class BookValidator {

    private Book validateBook = null;

    public BookValidator(Book book){
        this.validateBook = book;
    }

    public BookValidator(){

    }

    public boolean isValidTitle(String title){
        String regex = "[a-zA-z0-9\\s]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }

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

    public boolean isValidOwner(ArrayList<User> users, Book book){
        return users.contains(book.getOwner());
    }


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

    public boolean isOwned(User owner, Book book){
        return book.getOwner().equals(owner);
    }

}
