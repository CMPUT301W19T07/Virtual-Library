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

public class User {
    private String Username;
    private String Email;

    public User(String username, String email) {
        Username=username;
        Email=email;
    }

    public String getUsername() {
        return Username;
    }
}
