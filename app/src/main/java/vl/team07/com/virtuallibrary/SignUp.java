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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SignUp extends AppCompatActivity implements UserDataChecker{

    private String username;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void signUp(){


        EditText editText = (EditText) findViewById(R.id.Uname);
        username = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.Pword);
        password = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.Email);
        email = editText3.getText().toString();
        if(uniqueUsername(username)){
            if(checkEmail(email)){

            }
        }

    }

    public void Return(){
        finish();
    }

    @Override
    public boolean uniqueUsername(String uname) {
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }
}
