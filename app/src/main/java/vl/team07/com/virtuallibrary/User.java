/*
 * User Class
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains data about the user
 *
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
 */
public class User {

    private String UserName;
    private String Name;
    private String Password;
    private String Email;
    private int Age;
    private String Nationality;
    private int ContactInfo;    // Phone number
    private String Address;

    private List<Book> OwnedBookList;
    private List<Book> BorrowedBookList;

    public User(){
        this.UserName = "Test Username";
        this.Name = "Test Name";
    }
    //neccesary attributes to initialize user class are username, name and, email
    public User(String username, String name, String email) {
        this.UserName=username;
        this.Name = name;
        this.Email=email;
        Nationality = "Canada";
        Age = 20;
        OwnedBookList = new ArrayList<Book>();
        BorrowedBookList = new ArrayList<Book>();
    }

    public User(String username, String name, String password, String email, int age, String nationality, int contactInfo, String address){
        this.UserName = username;
        this.Name = name;
        this.Password = password;
        this.Email = email;
        this.Age = age;
        this.Nationality = nationality;
        this.ContactInfo = contactInfo;
        this.Address = address;
        this.OwnedBookList = new ArrayList<Book>();
        this.BorrowedBookList = new ArrayList<Book>();
    }

    /**
     * Set user name.
     *
     * @param inputUserName the input user name
     */
    public void setUserName(String inputUserName){this.UserName = inputUserName;}

    /**
     * Set name.
     *
     * @param inputName the input name
     */
    public void setName(String inputName){this.Name = inputName;}

    public void setPassword(String inputPassword){this.Password = inputPassword;}

    /**
     * Set email.
     *
     * @param inputEmail the input email
     */
    public void setEmail(String inputEmail){this.Email = inputEmail;}

    /**
     * Set age.
     *
     * @param inputAge the input age
     */
    public void setAge(int inputAge){this.Age = inputAge;}

    /**
     * Set nationality.
     *
     * @param inputNationality the input nationality
     */
    public void setNationality(String inputNationality){this.Nationality = inputNationality;}

    /**
     * Set contact info.
     *
     * @param inputContactInfo the input contact info
     */
    public void setContactInfo(int inputContactInfo){this.ContactInfo = inputContactInfo;}

    /**
     * Set address.
     *
     * @param inputAddress the input address
     */
    public void setAddress(String inputAddress){this.Address = inputAddress;}

    /**
     * Get user name string.
     *
     * @return the string
     */
    public String getUserName(){return this.UserName;}

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){return this.Name;}

    public String getPassword(){return this.Password;}

    /**
     * Get email string.
     *
     * @return the string
     */
    public String getEmail(){return this.Email;}

    /**
     * Get age int.
     *
     * @return the int
     */
    public int getAge(){return this.Age;}

    /**
     * Get nationality string.
     *
     * @return the string
     */
    public String getNationality(){return this.Nationality;}

    /**
     * Get contact info int.
     *
     * @return the int
     */
    public int getContactInfo(){return this.ContactInfo;}

    /**
     * Get address string.
     *
     * @return the string
     */
    public String getAddress(){return this.Address;}

    public void setOwnedBookList(ArrayList<Book> bookList){
        this.OwnedBookList = bookList;
    }

    public ArrayList<Book> getOwnedBookList(){
        ArrayList<Book> convertedBookList = new ArrayList<>();
        if (OwnedBookList != null) {
            for (Book book : OwnedBookList) {
                convertedBookList.add(book);
            }
        }
        return convertedBookList;
    }

    public void setBorrowedBookList(ArrayList<Book> bookList){
        this.BorrowedBookList = bookList;
    }

    public ArrayList<Book> getBorrowedBookList() {
        ArrayList<Book> convertedBookList = new ArrayList<>();
        if (BorrowedBookList != null){
            for (Book book : BorrowedBookList) {
                convertedBookList.add(book);
            }
        }
        return convertedBookList;
    }
}
