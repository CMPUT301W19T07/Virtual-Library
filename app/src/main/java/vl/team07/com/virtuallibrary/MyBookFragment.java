/*
 * MyBookFragment
 *
 * February 27, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class MyBookFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> myBookList;
    private ArrayList<Book> newBookList;

    SharedPreferences preferences;
    private DatabaseHandler databaseHandler;

    public MyBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View MyBookView = inflater.inflate(R.layout.fragment_my_book, container, false);

        recyclerView = (RecyclerView) MyBookView.findViewById(R.id.MyBookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myBookList = new ArrayList<>();

        adapter = new BookRecyclerViewAdapter(getContext(), myBookList);
        recyclerView.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(MyBookView.getContext());
        String current_userName = preferences.getString("current_userName", "n/a");

        databaseHandler = DatabaseHandler.getInstance(getActivity());
        databaseHandler.displayOwnedBooks(current_userName, adapter, myBookList);

        SwipeRefreshLayout pullToRefresh = MyBookView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myBookList.clear();
                adapter.notifyDataSetChanged();
                databaseHandler.displayOwnedBooks(current_userName, adapter, myBookList);
                pullToRefresh.setRefreshing(false);
            }
        });



        /**
         *Sets the onClickListener for each item in the Recycle View
         * and opens a book detail activity that recognizes that the clicked
         * book is owned by the current user
         *
         * Initially created by tianxin3 and further developed by pling
         *
         * @see RequestedBookDetailsActivity
         *
         */
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);
                Book clickedBook = myBookList.get(position);

                Context context = v.getContext();
                Intent intent = new Intent(context, MyBookDetailsActivity.class);
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

        //TempList();

        return MyBookView;
    }

    @Override
    public void onStart(){
        super.onStart();


    }

    public void TempList(){

        User user = new User("Test user", "Test name", "0", "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Fifth Book", "Fifth Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Seventh Book", "Seventh Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Ninth Book", "Ninth Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Eleventh Book", "Eleventh Author", "1234567890", "user", BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);

//        DatabaseHandler dh = new DatabaseHandler(getActivity());
//        myBookList = dh.retrieveAvailableBook();

    }


}