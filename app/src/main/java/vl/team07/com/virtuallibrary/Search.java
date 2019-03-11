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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Adapter;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private ArrayList<Book> allBookList;

    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager LayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        TempList();

        EditText editSearch = findViewById(R.id.editSearch);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text){
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : allBookList){
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(book);
            }
        }

        adapter.filterList(filteredList);
    }

    public void TempList(){
        allBookList = new ArrayList<>();
        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        //testBook = new Book("Second Book", "Second Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        //allBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        //testBook = new Book("Forth Book", "Forth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        //allBookList.add(testBook);
        testBook = new Book("Fifth Book", "Fifth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        //testBook = new Book("Sixth Book", "Sixth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        //allBookList.add(testBook);
        testBook = new Book("Seventh Book", "Seventh Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        //testBook = new Book("Eighth Book", "Eighth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        //allBookList.add(testBook);
        testBook = new Book("Ninth Book", "Ninth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        //testBook = new Book("Tenth Book", "Tenth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        //allBookList.add(testBook);
    }

    private void buildRecyclerView(){
        recyclerView = findViewById(R.id.SearchRecyclerView);
        recyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        adapter = new BookRecyclerViewAdapter(this, allBookList); //pls

        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
