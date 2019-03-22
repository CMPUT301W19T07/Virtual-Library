/*
 * Copyright <2019-1-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * Book Class
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

import java.util.ArrayList;

/**
 * Created by MTX on 2019-02-24.
 */

public class Book {

    private String Title;
    private String Author;
    private String ISBN;
    private User Owner;
    private BookStatus Status;
    private String Description;
    private String SearchString;
    private Image Image;
    private ArrayList<User> RequesterList = new ArrayList<>();

    public Book(){

        this.Owner = new User("Test", "Test","Test");
    }

//    public Book(String title, String author, int isbn, User owner, BookStatus status, String description, String searchString) {
//        this.Image = null;
//    }

    public Book(String title, String author, String isbn, User owner, BookStatus status, String description, String searchString, Image image){

        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Owner = owner;
        this.Status = status;
        this.Description = description;
        this.SearchString = searchString;
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

    public void setOwner(User inputOwner){this.Owner = inputOwner;}

    public void setStatus(BookStatus inputStatus){this.Status = inputStatus;}

    public void setDescription(String inputDescription){this.Description = inputDescription;}

    public void setSearchString(String inputSearchString){this.SearchString = inputSearchString;}

    public void setImage(Image image){this.Image = image;}

    public String getTitle(){return this.Title;}

    public String getAuthor(){return this.Author;}

    public String getISBN(){return this.ISBN;}

    public User getOwner(){return this.Owner;}

    public BookStatus getStatus(){return this.Status;}

    public String getDescription(){return  this.Description;}

    public String getSearchString(){return this.SearchString;}

    public Image getImage(){return this.Image;}

}
