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

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements UserDataChecker{

    private String username;
    private String name;
    private String email;
    private boolean Unique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void signUp(View view){

        EditText editText = (EditText) findViewById(R.id.Uname);
        username = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.Name);
        name = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.Email);
        email = editText3.getText().toString();
        if(uniqueUsername(username)){
            if(checkEmail(email)){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("Users").child(String.valueOf(new User(username,name,email)));
                Toast toast3 = Toast.makeText(getApplicationContext(),"Account added. You may now log in.",Toast.LENGTH_SHORT);
                toast3.show();
                Return(view);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"Email is invalid",Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            Toast toast2 = Toast.makeText(getApplicationContext(),"Username is already taken",Toast.LENGTH_SHORT);
            toast2.show();
        }

    }

    public void Return(View view){
        finish();
    }

    @Override
    public boolean uniqueUsername(final String uname) {
        Unique = true;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    User check = data.getValue(User.class);
                    if(uname == check.getUsername()){
                       Unique = false;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return Unique;
    }

    @Override
    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}
