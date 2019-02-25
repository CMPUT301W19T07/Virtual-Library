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
 * Created by MTX on 2019-02-24.
 */

public class UserValidator {

    private User validateUser;

    public UserValidator(User user){
        this.validateUser = user;
    }

    public UserValidator(){}

    public boolean isValidUsername(String username){
        String regex = "[a-zA-z]{6,20}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public boolean isValidEmail(String email){
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // Referenced: https://stackoverflow.com/questions/8204680/java-regex-email
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidAge(int age){
        return age >= 10 && age <= 100;
    }

    public boolean isValidName(String name){
        String regex = "[a-zA-z]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && name.length() <= 20;
    }

    public boolean isValidAddress(String address){
        // real address
        // related to map
        return true;
    }

    public boolean isExistedUsername(ArrayList<User> UserArrayList, final String username){
        // referenced:https://stackoverflow.com/questions/18852059/java-list-containsobject-with-field-value-equal-to-x
        boolean isExisted = UserArrayList.stream().anyMatch(user->user.getUserName().equals(username));
        return isExisted;
    }

}
