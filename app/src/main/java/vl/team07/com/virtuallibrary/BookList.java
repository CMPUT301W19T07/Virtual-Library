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

/**
 * Created by MTX on 2019-02-24.
 */

public class BookList {

    // This class is temporary used to test Book class and BookValidator class functionality.

    private ArrayList<Book> Books;
    private BookValidator bookValidator;

    public BookList(){
        Books = new ArrayList<>();
    }

    public void addBook(Book book){
        if(Books.contains(book)){
            throw new IllegalArgumentException("Contained");
        }
        Books.add(book);
    }

    public ArrayList<Book> getBooks(){
        return Books;
    }



}
