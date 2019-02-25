/*
 * Copyright <2019-1-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;

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