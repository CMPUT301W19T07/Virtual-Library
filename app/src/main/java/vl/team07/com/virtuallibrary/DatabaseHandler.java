/*
 * DatabaseHandler
 *
 * March 01, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */


package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This is the Firebase Database Handler. This class is designed to handle all the tasks that
 * are related to the Firebase Realtime Database. The TAG is gotten by getting the class name where
 * DatabaseHandler is instantiated and is converted to a simple string.
 *
 * @author Imtiaz Raqib
 * @version 1.0
 * @since 1.0
 */

public class DatabaseHandler {

    private final String TAG = getClass().getSimpleName();
    private FirebaseDatabase myDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Context context;

    /**
     * Constructor for the DatabaseHandler class which takes the context of which class it is in
     * when instantiated.
     * @param context
     */
    public DatabaseHandler(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance();
        databaseReference = myDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.context = context;
    }

    public void addBook(final Book book) {
        databaseReference.child("Books").child(String.valueOf(book.getISBN())).setValue(book);

        Toast toast = Toast.makeText(this.context, "Book is added!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();


    }

}
