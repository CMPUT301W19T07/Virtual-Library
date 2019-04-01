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

/**
 * The type Sign up.
 * Used to create new accounts.
 * @author cjmiller
 * @see LogIn,UserDataChecker
 * @version 1.0
 */
public class SignUp extends AppCompatActivity {

    private String username;
    private String name;
    private String email;
    private String password;
    private ProgressBar progressBar;
    private boolean Unique;
    private int age;
    private String nationality;

    FirebaseAuth firebaseAuth;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = preferences.edit();


        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) findViewById(R.id.userName);
                username = editText.getText().toString();
                EditText editText2 = (EditText) findViewById(R.id.Name);
                name = editText2.getText().toString();
                EditText editText3 = (EditText) findViewById(R.id.Email);
                email = editText3.getText().toString();
                EditText editText4 = findViewById(R.id.passWordSU);
                password = editText4.getText().toString();

                age = 18;
                nationality = "Canada";


                /**
                 * Signing up a new user using Firebase Authentication
                 *
                 * @author Imtiaz Raqib
                 */
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Registered successfully!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "ERROR: " + task.getException()
                                            .getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                User user = new User(username, name, email);

                DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());
                dh.addUser(user);

                edit.putString("current_userName", username);
                edit.commit();

                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);


            }
        });
    }

    /**
     * Sign up.
     * Collects the data entered and after check if it is correct, adds it to the database.
     * @param view the view
     */
    public void signUp(View view) {

    }

    /**
     * Return.
     * Returns to log In.
     * @param view the view
     */
    public void Return(View view){
        finish();
    }
}
