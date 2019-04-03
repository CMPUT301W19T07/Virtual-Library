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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.TextView;


/**
 * The type Log in.
 * Used to enter a user profile and then record it into the system so it can be referenced later.
 * @author cjmiller
 * @see SignUp
 * @version 1.0
 */
public class LogIn extends AppCompatActivity {

    private String userEmail;
    private String passWord;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    private String username;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = preferences.edit();

//        edit.putString("current_userName", "ronghui");
//        edit.commit();

        Button goToSignUpButton = findViewById(R.id.goToSignUp);
        goToSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        Button logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(v);
            }
        });
    }

    /**
     * Login.
     * Used to check if a userEmail exists in the database.
     * If it does, the profile is loaded into the system and the user is sent to the main page/
     * @param view the view
     */
    public void logIn(View view){

        EditText editText = (EditText) findViewById(R.id.userEmail);
        EditText editText2 = findViewById(R.id.passWordSU);
        progressBar = findViewById(R.id.progressBar);

        userEmail = editText.getText().toString();
        passWord = editText2.getText().toString();

        /**
         * Authorize user based on Firebase User Authentication
         *
         * @author Imtiaz Raqib
         */
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(userEmail, passWord).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    DatabaseHandler dh = DatabaseHandler.getInstance(LogIn.this);
                    dh.getUsernameToPref(userEmail, preferences, edit);
                    System.out.println("Current Email : "+ userEmail);
                    // Save username into PreferenceManager
//                    edit.putString("current_userName", username);
//                    edit.commit();

                    Intent intent = new Intent(LogIn.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogIn.this, "No account registered with "
                    + userEmail, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Signup.
     * Sends the user to the sign up page
     * @param view the view
     */
    public void signUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

}
