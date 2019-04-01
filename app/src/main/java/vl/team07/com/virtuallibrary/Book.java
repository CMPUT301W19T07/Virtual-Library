/*
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.

 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by MTX on 2019-02-24.
 */

public class Book {

    private String Title;
    private String Author;
    private String ISBN;
    private String Owner;
    private BookStatus Status;
    private String Description;
    private String SearchString;
    private String PickupLocation;
    private String Image;
    private ArrayList<User> RequesterList = new ArrayList<>();

    public Book(){

    }
    
    public Book(String title, String author, String isbn, String owner, BookStatus status, String description, String searchString) {
        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Owner = owner;
        this.Status = status;
        this.Description = description;
        this.SearchString = searchString;
        this.PickupLocation = "To Be Determined";
    }

    public Book(String title, String author, String isbn, String ownerUsername, BookStatus status, String description, String searchString, String image){

        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Owner = ownerUsername;
        this.Status = status;
        this.Description = description;
        this.SearchString = searchString;
        this.PickupLocation = "To Be Determined";
        this.Image = image;
    }

    public void addRequester(User requester){
        this.RequesterList.add(requester);
    }

    public ArrayList<User> getRequesters() {
        return this.RequesterList;
    }

    public void setTitle(String inputTitle){this.Title = inputTitle;}

    public void setAuthor(String inputAuthor){this.Author = inputAuthor;}

    public void setISBN(String inputISBN){this.ISBN = inputISBN;}

    public void setOwner(String ownerUsername){this.Owner = ownerUsername;}

    public void setStatus(BookStatus inputStatus){this.Status = inputStatus;}

    public void setDescription(String inputDescription){this.Description = inputDescription;}

    public void setSearchString(String inputSearchString){this.SearchString = inputSearchString;}

    public void setImage(String image){this.Image = image;}

    public String getTitle(){return this.Title;}

    public String getAuthor(){return this.Author;}

    public String getISBN(){return this.ISBN;}

    public String getOwner(){return this.Owner;}

    public BookStatus getStatus(){return this.Status;}

    public String getDescription(){return  this.Description;}

    public String getSearchString(){return this.SearchString;}

    public String getImage(){return this.Image;}

    public String getPickupLocation(){
        return this.PickupLocation;
    }

    public void setPickupLocation(String newPickupLocation){
        this.PickupLocation = newPickupLocation;
    }

}
