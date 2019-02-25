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

public class Book {

    private String Title;
    private String Author;
    private int ISBN;
    private User Owner;
    private BookStatus Status;
    private String Description;
    private String SearchString;
    private ArrayList<User> RequesterList = new ArrayList<>();

    public Book(){

        this.Title = "";
        this.Author = "";
        this.ISBN = 0;
        this.Owner = null;
        this.Status = BookStatus.AVAILABLE;
        this.Description = "";
        this.SearchString = "";
    }

    public Book(String title, String author, int isbn, User owner, BookStatus status, String description, String searchString){

        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Owner = owner;
        this.Status = status;
        this.Description = description;
        this.SearchString = searchString;
    }

    public void addRequester(User requester){
        this.RequesterList.add(requester);
    }

    public ArrayList<User> getRequesters() {
        return this.RequesterList;
    }

    public void setTitle(String inputTitle){this.Title = inputTitle;}

    public void setAuthor(String inputAuthor){this.Author = inputAuthor;}

    public void setISBN(int inputISBN){this.ISBN = inputISBN;}

    public void setOwner(User inputOwner){this.Owner = inputOwner;}

    public void setStatus(BookStatus inputStatus){this.Status = inputStatus;}

    public void setDescription(String inputDescription){this.Description = inputDescription;}

    public void setSearchString(String inputSearchString){this.SearchString = inputSearchString;}

    public String getTitle(){return this.Title;}

    public String getAuthor(){return this.Author;}

    public int getISBN(){return this.ISBN;}

    public User getOwner(){return this.Owner;}

    public BookStatus getStatus(){return this.Status;}

    public String getDescription(){return  this.Description;}

    public String getSearchString(){return this.SearchString;}

}
