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
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.io.ByteArrayOutputStream;
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

    private FirebaseStorage myDbStorage;
    private StorageReference myDbStorageRef;

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

//    public ArrayList<Book> retrieveAvailableBook() {
//
//        databaseReference.keepSynced(true);
//        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
//                    Book book = adSnapshot.getValue(Book.class);
//                    newBookList.add(book);
//                }
//                System.out.println("Size of the list in onDataChange is: " + newBookList.size());
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        System.out.println("Size of the list outside onDataChange is: " + newBookList.size());
//        return newBookList;
//    }


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

    /**
     * This method takes in a User object and adds it to the database using the values provided
     * during the signUp process
     *
     * @param user
     * @see SignUp
     */
    public void addUser(User user) {
        databaseReference.child("Users").child(user.getUserName()).setValue(user);

        createToast("You are registered");
    }

    /**
     * This method takes in a book object and a review object to add a review to the database under
     * the ISBN of the of clicked book
     *
     * @param book
     * @param review
     * @see AddReviewActivity
     */
    public void addReview (Book book, Review review) {
        databaseReference.child("Reviews").child(String.valueOf(book.getISBN())).setValue(review);
    }

    /**
     * This method takes the image uploaded while adding a book to the Firebase storage. It then
     * takes the URL related to the stored image and stores it in the Book Database
     *
     * @param bmp
     * @param book
     * @see AddBookFragment
     */

    public void uploadImageToFirebase (Bitmap bmp, Book book) {
        myDbStorage = FirebaseStorage.getInstance();
        myDbStorageRef = myDbStorage.getReference();

        final StorageReference ImagesRef = myDbStorageRef.child("images/"+book.getISBN()+".png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        final UploadTask uploadTask = ImagesRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                createToast("Failed to add image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.i("problem", task.getException().toString());
                        }

                        return ImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri imageURI = task.getResult();
                            DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference()
                                    .child("Books").child(String.valueOf(book.getISBN()));

                            bookRef.child("image").setValue(imageURI.toString());
                        }
                        else {
                            createToast("Failed!");
                        }
                    }
                });
            }
        });

    }

    /**
     * Simple method to generate Toast in Android
     * @param toastText
     */

    public void createToast(String toastText) {
        Toast toast = Toast.makeText(this.context, toastText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
    }


}