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

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AllBookFragment extends android.support.v4.app.Fragment {


    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> allBookList;
    private ArrayList<Book> availableBookList;
    private ArrayList<Book> borrowedBookList;
    private ArrayAdapter<Book> arrayAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private final String BOOK_PARENT = "All Books";
    private final String BOOK_AVAILABLE = BookStatus.AVAILABLE.toString();
    private final String BOOK_BORROWED = BookStatus.BORROWED.toString();

    public AllBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View AllBookView = inflater.inflate(R.layout.fragment_all_book, container, false);

        recyclerView = (RecyclerView) AllBookView.findViewById(R.id.AllBookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allBookList = new ArrayList<>();
        availableBookList = new ArrayList<>();
        borrowedBookList = new ArrayList<>();
        adapter = new BookRecyclerViewAdapter(getContext(), allBookList);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        // int position is index of item clicked in recyclerView
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);
            }
        });


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        return AllBookView;
    }


    public void onStart(){
        super.onStart();
        loadFromFirebase();
        TempList();

    }

    public void loadFromFirebase(){

        databaseReference.keepSynced(true);
        databaseReference.child(BOOK_PARENT).child(BOOK_AVAILABLE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Book availableBook = data.getValue(Book.class);
                    if(availableBook!=null){
                        allBookList.add(availableBook);
                        System.out.println("BOOK IS: "+availableBook);
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


    // Temp for test
    public void TempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Second Book", "Second Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
//        testBook = new Book("Third Book", "Third Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Forth Book", "Forth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Fifth Book", "Fifth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Sixth Book", "Sixth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Seventh Book", "Seventh Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Eighth Book", "Eighth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Ninth Book", "Ninth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Tenth Book", "Tenth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
    }
}