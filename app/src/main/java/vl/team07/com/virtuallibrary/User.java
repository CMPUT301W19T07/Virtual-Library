/*
 * Class Name : User
 *
 * Date of Initiation: 23rd of February
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

public class User {
    private String Username;
    private String Email;
    private String Name;
    private int Age;
    private String Nationality;

    //neccesary attributes to initialize user class are username, name and, email
    public User(String username, String name, String email) {
        this.Username=username;
        this.Name = name;
        this.Email=email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username){
        this.Username = username;
    }

    public String getEmail(){
        return this.Email;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public int getAge(){
        return this.Age;
    }

    public void setAge(int age){
        this.Age = age;
    }

    public String setNationality(){
        return this.Nationality;
    }

    public void getNationality(String nationality){
        this.Nationality = nationality;
    }
}
