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
import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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

    private ArrayList<Book> newBookList = new ArrayList<>();

    /**
     * Constructor for the DatabaseHandler class which takes the context of which class it is in
     * when instantiated.
     *
     * @param context
     */
    public DatabaseHandler(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance();
        databaseReference = myDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.context = context;
    }

    /**
     * This method adds a book to the database using the ISBN as unique child key.
     *
     * @param book
     * @see AddBookFragment
     */
    public void addBook(final Book book) {
        databaseReference.child("Books").child(String.valueOf(book.getISBN())).setValue(book);

        Toast toast = Toast.makeText(this.context, "Book is added!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();

    }

    /**
     * This method retrieves all the books from the database under child Books and returns it as
     * an arraylist of type Boook
     *
     * @return ArrayList
     * @see MyBookFragment
     */

    public ArrayList<Book> retrieveAvailableBook() {

        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Book book = adSnapshot.getValue(Book.class);
                    newBookList.add(book);
                }
                System.out.println("Size of the list in onDataChange is: " + newBookList.size());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("Size of the list outside onDataChange is: " + newBookList.size());
        return newBookList;
    }


//    public void retrieveAvailableBook() {
//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Book retrievedBook = dataSnapshot.getValue(Book.class);
//                System.out.println("Author: " + retrievedBook.getAuthor());
//                System.out.println("Title: " + retrievedBook.getTitle());
//                System.out.println("Desc: " + retrievedBook.getDescription());
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void addUser(User user) {
        databaseReference.child("Users").child(user.getUserName()).setValue(user);

        Toast toast = Toast.makeText(this.context, "You are registered", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
    }


}