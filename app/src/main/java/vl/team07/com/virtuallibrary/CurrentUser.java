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
 * The type Current user.
 * Holds the data of the User currently logged in.
 * Used for displaying the User's profile in addition to finding their books.
 */
public class CurrentUser {

    private static CurrentUser Cuser = null;

    /**
     * The Username of the User.
     */
    public String Username;
    /**
     * The Email of the User.
     */
    public String Email;
    /**
     * The Name of the User.
     */
    public String Name;
    /**
     * The Age of the User.
     */
    public int Age;
    /**
     * The Nationality of the User.
     */
    public String Nationality;


    private CurrentUser()
    {
        Username = "";
        Email = "";
        Name = "";
        Age = 0;
        Nationality = "";
    }

    /**
     * Gets instance.
     *
     * @return the instance of Currentuser created
     */
    public static CurrentUser getInstance()
    {
        if (Cuser == null)
            Cuser = new CurrentUser();

        return Cuser;
    }
}
