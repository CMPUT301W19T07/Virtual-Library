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
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
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
import java.util.List;

import static java.sql.DriverManager.println;

import java.util.ArrayList;


public class SearchFragment extends android.support.v4.app.Fragment {

    private EditText search;
    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> availableBookList;

    SharedPreferences preferences;

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
        recyclerView = (RecyclerView) SearchView.findViewById(R.id.SearchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        availableBookList = new ArrayList<>();
        adapter = new BookRecyclerViewAdapter(getContext(), availableBookList);
        recyclerView.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(SearchView.getContext());
        String current_userName = preferences.getString("current_userName", "n/a");

        DatabaseHandler dh = DatabaseHandler.getInstance(getActivity());
        dh.displayAvailableBooks(current_userName, adapter, availableBookList);

        getActivity().setTitle("Search");
        search = SearchView.findViewById(R.id.SearchEditText);
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

        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: " + position);
                Book clickedBook = availableBookList.get(position);

                Context context = v.getContext();

                if(clickedBook.getStatus()== BookStatus.AVAILABLE){
                    Intent intent = new Intent(context, OwnerBookDetailsActivity.class);
                    String title = clickedBook.getTitle();
                    String author = clickedBook.getAuthor();
                    String isbn = clickedBook.getISBN();
                    String pickupLocation = clickedBook.getPickupLocation();
                    String description = clickedBook.getDescription();

                    Bundle extras = new Bundle();
                    extras.putString("TITLE", title);
                    extras.putString("AUTHOR", author);
                    extras.putString("ISBN", isbn);
                    extras.putString("PICKUPLOCATION", pickupLocation);
                    extras.putString("DESCRIPTION", description);
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            }
        });

        tempList();
        return SearchView;
    }

    public boolean isMatch(String search, String subject){
        return subject.toLowerCase().contains(search.toLowerCase());
    }

    private void filter(String text){
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book books : availableBookList){
            if (isMatch(text, books.getTitle()) || isMatch(text, books.getDescription()) || isMatch(text,books.getAuthor())){
                filteredList.add(books);
            }
        }
        adapter.filterList(filteredList);
    }

    public void tempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", "1234567890", "user", BookStatus.AVAILABLE, "Description1","SSN",null);
        availableBookList.add(testBook);
        testBook = new Book("Second Book", "Second Author", "1234567890", "user", BookStatus.AVAILABLE, "Description2","SSN",null);
        availableBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", "1234567890", "user3", BookStatus.AVAILABLE, "Book","SSN",null);
        availableBookList.add(testBook);
        testBook = new Book("Forth Book", "Forth Author", "1234567890", "user4", BookStatus.AVAILABLE, "Test","SSN",null);
        availableBookList.add(testBook);

    }

}

