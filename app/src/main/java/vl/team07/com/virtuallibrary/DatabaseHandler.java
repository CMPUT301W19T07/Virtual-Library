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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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


    private static DatabaseHandler instance;
    private final String TAG = getClass().getSimpleName();
    private FirebaseDatabase myDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Context context;

    private ArrayList<Book> newBookList = new ArrayList<>();
    private ArrayList<Review> newReviewList = new ArrayList<>();

    private final String BOOK_PARENT = "Books";
    private final String BOOK_AVAILABLE = BookStatus.AVAILABLE.toString();
    private final String BOOK_BORROWED = BookStatus.BORROWED.toString();




    /**
     * Constructor for the DatabaseHandler class which takes the context of which class it is in
     * when instantiated.
     *
     * @param context
     */
    private DatabaseHandler(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance();
        databaseReference = myDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.context = context;
    }

    public static DatabaseHandler getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    /**
     * This method adds a book to the database using the ISBN as unique child key.
     *
     * @param book
     * @see AddBookFragment
     */
    public void addBook(final Book book) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String title = "Error";
                final String message = "The book has already exist in the library!";

                if(dataSnapshot.child(BOOK_PARENT).child(book.getISBN()).exists()) {
                    alertDialog(title, message);
                }else{
                    databaseReference.child("Books").child(book.getISBN()).setValue(book);

                    showToast("The book is added!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//
//        databaseReference.child("Books").child(String.valueOf(book.getISBN())).setValue(book);
//
//        Toast toast = Toast.makeText(this.context, "Book is added!", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
//        toast.show();
    }

    /**
     * This method retrieves all the books from the database under child Books and returns it as
     * an arraylist of type Boook
     *
     * @return ArrayList
     * @see MyBookFragment
     */

    public ArrayList<Book> retrieveAvailableBook() {

        databaseReference.keepSynced(true);
        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Book book = adSnapshot.getValue(Book.class);
                    newBookList.add(book);
                }
                System.out.println("Size of the list in onDataChange is: " + newBookList.size());
                DatabaseHandler db = getInstance(context);
                db.setNewBookList(newBookList);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("Size of the list outside onDataChange is: " + newBookList.size());
        System.out.println("Title of the third book is " + newBookList.get(2));
        return newBookList;
    }



    //Add newArrayList to database's newBookList
    public void setNewBookList(ArrayList<Book> bookList){
        this.newBookList = bookList;
    }

    public ArrayList<Review> retrieveBookReviews(String ISBN) {

        databaseReference.keepSynced(true);
        DatabaseReference reviewref = databaseReference.child("Reviews");
        reviewref.child(ISBN).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Review> currentReviewList = new ArrayList<Review>();
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Review review = adSnapshot.getValue(Review.class);
                    System.out.println("Comment of review is "+ review.getComment());
                    currentReviewList.add(review);
                }
                System.out.println("Size of the list in onDataChange is: " + currentReviewList.size());
                DatabaseHandler db = getInstance(context);
                db.setReviewList(currentReviewList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("Size of the list outside onDataChange is: " + newReviewList.size());
        return this.newReviewList;
    }


    public void setReviewList(ArrayList<Review> reviewList){
        this.newReviewList = reviewList;
    }


    /**
     * This method takes in a User object and adds it to the database using the values provided
     * during the signUp process
     *
     * @param user
     * @see SignUp
     */
    public void addUser(User user) {
        databaseReference.child("Users").child(user.getUserName()).setValue(user);

        Toast toast = Toast.makeText(this.context, "You are registered", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
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
        databaseReference.child("Reviews").child(String.valueOf(book.getISBN())).child(review.getReviewer()).setValue(review);
    }


    /**
     * This method takes in a book array list and a book recyclerview adapter to load book from firebase
     *
     * @param allBookList
     * @param adapter
     * @see AllBookFragment
     */
    public void loadAllBook(ArrayList<Book> allBookList, BookRecyclerViewAdapter adapter){
        databaseReference.keepSynced(true);
        databaseReference.child(BOOK_PARENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Book availableBook;
                    availableBook = data.getValue(Book.class);

                    if(availableBook!=null){
                        allBookList.add(availableBook);
                    }
                }
//                adapter.setBookList(allBookList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void loadReviews(ArrayList<Review> reviewList, ReviewRecyclerViewAdapter adapter, String ISBN){
        databaseReference.keepSynced(true);
        DatabaseReference reviewref = databaseReference.child("Reviews");
        reviewref.child(ISBN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Review review = data.getValue(Review.class);

                    if(review!=null){
                        reviewList.add(review);
                    }
                }
//                adapter.setBookList(allBookList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    /**
     * This method is takes in title string and message string to give alert dialog
     * @param title
     * @param message
     */
    public void alertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public void showToast(String message){

        Toast toast = Toast.makeText(this.context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
    }


}