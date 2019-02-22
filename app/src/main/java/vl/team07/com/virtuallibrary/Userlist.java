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

public class Userlist {
    ArrayList<User> data = new ArrayList<>();

    public void addUser(User u){
        data.add(u);
    }

    public boolean checkusername(String test) {
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getUsername().equals(test)){
                return true;
            }
        }
        return false;
    }

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

    public boolean UniqueUsername(String test) {
        for (int i = 0; i < data.size(); i++){
            if(data.get(i).getUsername().equals(test)){
                return true;
            }
        }
        return false;
    }


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
