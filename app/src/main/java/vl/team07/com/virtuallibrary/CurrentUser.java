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

public class CurrentUser {

    private static CurrentUser Cuser = null;

    public String Username;
    public String Email;
    public String Name;
    public int Age;
    public String Nationality;

    private CurrentUser()
    {
        Username = "";
        Email = "";
        Name = "";
        Age = 0;
        Nationality = "";
    }

    public static CurrentUser getInstance()
    {
        if (Cuser == null)
            Cuser = new CurrentUser();

        return Cuser;
    }
}
