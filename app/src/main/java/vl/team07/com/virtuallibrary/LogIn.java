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
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * The type Log in.
 * Used to enter a user profile and then record it into the system so it can be referenced later.
 * @author cjmiller
 * @see SignUp
 * @version 1.0
 */
public class LogIn extends AppCompatActivity{

    private String username;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = preferences.edit();

        edit.putString("current_userName", "null");
        edit.commit();

        Button goToSignUpButton = findViewById(R.id.goToSignUp);
        goToSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        Button logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    /**
     * Login.
     * Used to check if a username exists in the database.
     * If it does, the profile is loaded into the system and the user is sent to the main page/
     * @param view the view
     */
    public void login(View view){

        EditText editText = (EditText) findViewById(R.id.USERNAME);
        String test = "test";
        editText.setText(test);
        username = editText.getText().toString();

        if (username.equalsIgnoreCase("test")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

//                for(//get firebase data here){
//                    User user = (User) "data here";
//                    if(username == user.getUsername()){
//                        CurrentUser u = CurrentUser.getInstance();
//                        u.Username = user.getUsername();
//                        u.Name = user.getName();
//                        u.Email = user.getEmail();
//                        u.Age = user.getAge();
//                        u.Nationality = user.getNationality();
//                        Intent intent = new Intent(this, MainPage.class);
//                        startActivityForResult(intent,0);
//                    }
//                Toast toast = Toast.makeText(getApplicationContext(),"User profile not found",Toast.LENGTH_SHORT);
//                toast.show();
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
