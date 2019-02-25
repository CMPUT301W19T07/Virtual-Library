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

public class ComposeBook {

    private String Title;
    private String Author;
    private int ISBN;
    private String Description;
    private String SearchStringName;

    public ComposeBook(){

    }
    public ComposeBook(String title, String author, int isbn,String description){
        this.Title = title;
        this.Author = author;
        this.ISBN = isbn;
        this.Description = description;
    }

    public String createSSN(String title, String author, String descriptione){
        this.SearchStringName = title + "/" + author + "/" + descriptione;
        return this.SearchStringName;
    }

    public String createSSN(){
        this.SearchStringName = this.Title + "/" + this.Author + "/" + this.Description;
        return this.SearchStringName;
    }
}
