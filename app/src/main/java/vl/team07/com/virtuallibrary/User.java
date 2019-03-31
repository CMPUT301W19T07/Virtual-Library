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
    private int Password;
    private String Email;
    private int Age;
    private String Nationality;
    private int ContactInfo;    // Phone number
    private String Address;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param name     the name
     * @param email    the email
     */
//neccesary attributes to initialize user class are username, name and, email
    public User(String username, String name, String email) {
        this.UserName=username;
        this.Name = name;
        this.Email=email;
        Age = 0;
        Nationality = null;
    }

    /**
     * Instantiates a new User.
     *
     * @param username    the username
     * @param name        the name
     * @param password    the password
     * @param email       the email
     * @param age         the age
     * @param nationality the nationality
     * @param contactInfo the contact info
     * @param address     the address
     */
    public User(String username, String name, int password, String email, int age, String nationality, int contactInfo, String address){
        this.UserName = username;
        this.Name = name;
        this.Password = password;
        this.Email = email;
        this.Age = age;
        this.Nationality = nationality;
        this.ContactInfo = contactInfo;
        this.Address = address;
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

    /**
     * Set password.
     *
     * @param inputPassword the input password
     */
    public void setPassword(int inputPassword){this.Password = inputPassword;}

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

    /**
     * Get password int.
     *
     * @return the int
     */
    public int getPassword(){return this.Password;}

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
}
