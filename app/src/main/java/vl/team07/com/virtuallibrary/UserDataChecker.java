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
 * The interface User data checker.
 * Used to check if a username or email is valid.
 * @deprecated 1.0
 */
public interface UserDataChecker {

    /**
     * Unique username boolean.
     * Checks if the database already has a user profile with the entered username
     * @param uname the uname
     * @return the boolean
     */
    boolean uniqueUsername(String uname);

    /**
     * Check email boolean.
     * Checks to see if a entered email is in a valid format.
     * @param email the email
     * @return the boolean
     */
    boolean checkEmail(String email);
}
