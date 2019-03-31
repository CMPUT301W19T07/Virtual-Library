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
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;
import android.widget.EditText;

import java.util.ArrayList;


public class SearchFragment extends android.support.v4.app.Fragment {

   // private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> allBookList;
    EditText search;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View SearchView = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().setTitle("Search");


        search = SearchView.findViewById(R.id.searchText);
        search.addTextChangedListener(new TextWatcher() {
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
        RecyclerView mRecycler = SearchView.findViewById(R.id.SearchRecyclerView);
        adapter = new BookRecyclerViewAdapter(getContext(),allBookList);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecycler.setAdapter(adapter);
        TempList();

        return SearchView;
    }
    private void filter(String text){
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book books : allBookList){
            if (books.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(books);
            }
        }
        adapter.filterList(filteredList);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM); item.setActionView(searchView);
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("TEST....", "Search Query Submitted");

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TEST 2....", "tap");
                return false;
            }
        });
    }

    public void TempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Second Book", "Second Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Forth Book", "Forth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Fifth Book", "Fifth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Sixth Book", "Sixth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Seventh Book", "Seventh Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Eighth Book", "Eighth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Ninth Book", "Ninth Author", 1234567890, user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Tenth Book", "Tenth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
    }
}

