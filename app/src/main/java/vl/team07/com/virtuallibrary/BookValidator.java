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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MTX on 2019-02-24.
 */

public class BookValidator {

    private Book validateBook;

    public BookValidator(Book book){
        this.validateBook = book;
    }

    public BookValidator(){

    }

    public boolean isValidISBN(int ISBN){
        String regex = "\\d{10}|\\d{13}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Integer.toString(ISBN));
        return matcher.matches();
    }

    public boolean isValidStatus(){
        return this.validateBook.getStatus().equals(BookStatus.AVAILABLE);
    }

    public boolean isMatchSSN(String givenSSN){
        return this.validateBook.getSearchString().contains(givenSSN);
    }

    public boolean isOwned(User owner){
        return this.validateBook.getOwner().equals(owner);
    }
}
