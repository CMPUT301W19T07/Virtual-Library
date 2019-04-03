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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Userlist.
 * Used for testing purposes
 * @deprecated 1.0
 */
public class Userlist {
    /**
     * The Data.
     */
    ArrayList<User> data = new ArrayList<>();

    /**
     * Add user.
     *
     * @param u the u
     */
    public void addUser(User u){
        data.add(u);
    }

    /**
     * Checkusername boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public boolean checkusername(String test) {
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getUserName().equals(test)){
                return true;
            }
        }
        return false;
    }

    /**
     * Unique username boolean.
     *
     * @param test the test
     * @return the boolean
     */
/*
    public boolean checkpassword(String Utest, String Ptest) {
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getUsername().equals(Utest)){
                if(data.get(i).getPassword().equals(Ptest)){
                    return true;
                }
            }
        }
        return false;
    }
*/
    public boolean UniqueUsername(String test) {
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getUserName().equals(test)){
                return true;
            }
        }
        return false;
    }


    /**
     * Valid email boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public boolean ValidEmail(String test) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(test);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}
