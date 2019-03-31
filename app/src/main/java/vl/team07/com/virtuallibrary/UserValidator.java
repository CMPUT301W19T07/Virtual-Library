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
 * Used to validate data about the user
 *
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
 * @deprecated 1.0
 */
public class UserValidator {

    private User validateUser;

    /**
     * Instantiates a new User validator.
     *
     * @param user the user
     */
    public UserValidator(User user){
        this.validateUser = user;
    }

    /**
     * Instantiates a new User validator.
     */
    public UserValidator(){}

    /**
     * Is valid username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isValidUsername(String username){
        String regex = "[a-zA-z]{6,20}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isValidEmail(String email){
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // Referenced: https://stackoverflow.com/questions/8204680/java-regex-email
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Is valid age boolean.
     *
     * @param age the age
     * @return the boolean
     */
    public boolean isValidAge(int age){
        return age >= 10 && age <= 100;
    }

    /**
     * Is valid name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean isValidName(String name){
        String regex = "[a-zA-z]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && name.length() <= 20;
    }

    /**
     * Is valid address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    public boolean isValidAddress(String address){
        // real address
        // related to map
        return true;
    }

    /**
     * Is existed username boolean.
     *
     * @param UserArrayList the user array list
     * @param username      the username
     * @return the boolean
     */
    public boolean isExistedUsername(ArrayList<User> UserArrayList, final String username){
        // referenced:https://stackoverflow.com/questions/18852059/java-list-containsobject-with-field-value-equal-to-x
        boolean isExisted = UserArrayList.stream().anyMatch(user->user.getUserName().equals(username));
        return isExisted;
    }

}
