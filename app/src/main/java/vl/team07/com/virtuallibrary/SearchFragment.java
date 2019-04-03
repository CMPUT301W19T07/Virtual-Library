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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class SearchFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> availableBookList;
    private ArrayList<User> searchedUserList;

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

        Button searchWithTermsButton = SearchView.findViewById(R.id.SearchTermsButton);


        preferences = PreferenceManager.getDefaultSharedPreferences(SearchView.getContext());
        String current_userName = preferences.getString("current_userName", "n/a");
        System.out.println("Current user's username: " + current_userName);

        DatabaseHandler dh = DatabaseHandler.getInstance(getActivity());
        dh.displayAvailableBooks(current_userName, adapter, availableBookList);

        getActivity().setTitle("Search");

        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);
                Book clickedBook = availableBookList.get(position);

                Context context = v.getContext();
                Intent intent = new Intent(context, OtherBookDetailsActivity.class);
                String title = clickedBook.getTitle();
                String author = clickedBook.getAuthor();
                BookStatus status_enum = clickedBook.getStatus();
                String status = status_enum.name();
                String isbn = clickedBook.getISBN();
                String pickupLocation = clickedBook.getPickupLocation();
                String description = clickedBook.getDescription();
                String owner = clickedBook.getOwner();

                Bundle extras = new Bundle();
                extras.putString("TITLE", title);
                extras.putString("AUTHOR", author);
                extras.putString("ISBN", isbn);
                extras.putString("PICKUPLOCATION", pickupLocation);
                extras.putString("DESCRIPTION", description);
                extras.putString("STATUS", status);
                extras.putString("OWNER", owner);
                intent.putExtras(extras);
                context.startActivity(intent);

            }
        });

        searchWithTermsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchBar = SearchView.findViewById(R.id.SearchEditText);
                String searchTerms = searchBar.getText().toString();

                if(searchTerms.charAt(0) == '@'){
                    UserRecyclerViewAdapter userAdapter = new UserRecyclerViewAdapter(getContext(), searchedUserList);
                    recyclerView.setAdapter(userAdapter);

                    String searchedUserName = searchTerms.replace("@", "");

                    DatabaseHandler dh = DatabaseHandler.getInstance(getActivity());
                    dh.SearchUserName(searchedUserName, userAdapter, searchedUserList);

                }
                else {
                    preferences = PreferenceManager.getDefaultSharedPreferences(SearchView.getContext());
                    String current_userName = preferences.getString("current_userName", "n/a");
                    System.out.println("Current user's username: " + current_userName);

                    DatabaseHandler dh = DatabaseHandler.getInstance(getActivity());
                    dh.getBooksWithTerms(current_userName, adapter, availableBookList, searchTerms);

                    //hides keyboard after pressing button
                    InputMethodManager inputManager = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow((null == getActivity().getCurrentFocus()) ? null : getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        return SearchView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.search, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM); item.setActionView(searchView);
////        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
////        MenuItemCompat.setActionView(item, sv);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                Log.d("TEST....", "Search Query Submitted");
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                Log.d("TEST 2....", "tap");
//                return true;
//            }
//        });
//    }
}

