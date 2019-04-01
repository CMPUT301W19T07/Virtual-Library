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
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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


public class SearchFragment extends android.support.v4.app.Fragment {

    RecyclerView recyclerView;
    public ArrayList<Book> availBookList; //Books that are available and not yours.
    public BookRecyclerViewAdapter mAdapter;
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().setTitle("Search");

        recyclerView = (RecyclerView) view.findViewById(R.id.SearchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        availBookList = new ArrayList<>();
        mAdapter = new BookRecyclerViewAdapter(getContext(), availBookList);
        recyclerView.setAdapter(mAdapter);

        search = view.findViewById(R.id.searchText);
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

        mAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: " + position);
                Book clickedBook = availBookList.get(position);

                Context context = v.getContext();

                if(clickedBook.getStatus()== BookStatus.AVAILABLE){
                    System.out.println("please " + position);
                    Intent intent = new Intent(context, NonOwnerBookDetailsActivity.class);
                    String title = clickedBook.getTitle();
                    String author = clickedBook.getAuthor();
                    int isbn = clickedBook.getISBN();
                    String ownerAddress = clickedBook.getOwner().getAddress();
                    String description = clickedBook.getDescription();

                    Bundle extras = new Bundle();
                    extras.putString("TITLE", title);
                    extras.putString("AUTHOR", author);
                    extras.putInt("ISBN", isbn);
                    extras.putString("OWNERADDRESS", ownerAddress);
                    extras.putString("DESCRIPTION", description);
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            }
        });

        tempList();
        return view;
    }

    public boolean isMatch(String search, String subject){
        return subject.toLowerCase().contains(search.toLowerCase());
    }

    private void filter(String text){
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book books : availBookList){
            if (isMatch(text, books.getTitle()) || isMatch(text, books.getDescription()) || isMatch(text,books.getAuthor())){
                filteredList.add(books);
            }
        }
        mAdapter.filterList(filteredList);
    }

    public void tempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", 1234567890, user, BookStatus.AVAILABLE, "Description1","SSN",null);
        availBookList.add(testBook);
        testBook = new Book("Second Book", "Second Author", 1234567890, user, BookStatus.AVAILABLE, "Description2","SSN",null);
        availBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", 1234567890, user, BookStatus.AVAILABLE, "Book","SSN",null);
        availBookList.add(testBook);
        testBook = new Book("Forth Book", "Forth Author", 1234567890, user, BookStatus.AVAILABLE, "Test","SSN",null);
        availBookList.add(testBook);

    }

}

