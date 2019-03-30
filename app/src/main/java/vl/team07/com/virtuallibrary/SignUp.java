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

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        firebaseAuth = FirebaseAuth.getInstance();

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

                DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
                dh.addUser(user);

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





//        if(uniqueUsername(username)){
//            if(checkEmail(email)){
//                //database here
//                myRef.child("Users").child(String.valueOf(new User(username,name,email)));
//                Toast toast3 = Toast.makeText(getApplicationContext(),"Account added. You may now log in.",Toast.LENGTH_SHORT);
//                toast3.show();
//                Return(view);
//            }else{
//                Toast toast = Toast.makeText(getApplicationContext(),"Email is invalid",Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        }else{
//            Toast toast2 = Toast.makeText(getApplicationContext(),"Username is already taken",Toast.LENGTH_SHORT);
//            toast2.show();
//        }


    /**
     * Return.
     * Returns to log In.
     * @param view the view
     */
    public void Return(View view){
        finish();
    }

//    /**
//     * Implementation of the uniqueUsername method
//     * @param uname the string: The username needed to be checked
//     * @return Unique the boolean: True if the username is not present in the darabase
//     */
//    @Override
//    public boolean uniqueUsername(final String uname) {
//        Unique = true;
//                for(DataSnapshot data:){ //get data here
//                    User check = data.getValue(User.class);
//                    if(uname == check.getUsername()){
//                       Unique = false;
//                    }
//                }
//        return Unique;


//    /**
//     * Implementation of the checkEmail method
//     * @param email the string: The email the user wishes to use
//     * @return True if the email is in a valid format, otherwise false
//     */
//    @Override
//    public boolean checkEmail(String email) {
//        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(email);
//        if(matcher.matches()){
//            return true;
//        }else{
//            return false;
//        }
//    }
}
