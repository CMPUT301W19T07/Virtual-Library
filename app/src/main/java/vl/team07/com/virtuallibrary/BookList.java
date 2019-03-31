/*
 * BookList Class
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;

/**
 *
 * A test class used to ensure that Book and related classes function properly
 *
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
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

    public boolean hasBook(Book book){
        return Books.contains(book);
    }

}

