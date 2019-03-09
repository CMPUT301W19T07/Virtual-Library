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

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The type Log in.
 * Used to enter a user profile and then record it into the system so it can be referenced later.
 */
public class LogIn extends AppCompatActivity{

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    /**
     * Login.
     * Used to check if a username exists in the database.
     * If it does, the profile is loaded into the system and the user is sent to the main page/
     * @param view the view
     */
    public void login(View view){

        EditText editText = (EditText) findViewById(R.id.USERNAME);
        username = editText.getText().toString();

                for(//get firebase data here){
                    User user = (User) "data here";
                    if(username == user.getUsername()){
                        CurrentUser u = CurrentUser.getInstance();
                        u.Username = user.getUsername();
                        u.Name = user.getName();
                        u.Email = user.getEmail();
                        u.Age = user.getAge();
                        u.Nationality = user.getNationality();
                        Intent intent = new Intent(this, MainPage.class);
                        startActivityForResult(intent,0);
                    }
                Toast toast = Toast.makeText(getApplicationContext(),"User profile not found",Toast.LENGTH_SHORT);
                toast.show();
    }

    /**
     * Signup.
     * Sends the user to the sign up page
     * @param view the view
     */
    public void signup(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

}
