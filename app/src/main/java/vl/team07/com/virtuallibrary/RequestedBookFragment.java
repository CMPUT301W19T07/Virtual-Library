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
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RequestedBookFragment extends android.support.v4.app.Fragment {


    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> requestedBookList;
    private DatabaseHandler databaseHandler;
    SharedPreferences preferences;


    private final String BOOK_PARENT = "Books";
    private final String BOOK_AVAILABLE = BookStatus.AVAILABLE.toString();
    private final String BOOK_BORROWED = BookStatus.BORROWED.toString();

    public RequestedBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RequestedBookView = inflater.inflate(R.layout.fragment_all_book, container, false);

        recyclerView = (RecyclerView) RequestedBookView.findViewById(R.id.AllBookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        requestedBookList = new ArrayList<>();

        adapter = new BookRecyclerViewAdapter(getContext(), requestedBookList);
        recyclerView.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(RequestedBookView.getContext());
        String current_userName = preferences.getString("current_userName", "n/a");

        databaseHandler = DatabaseHandler.getInstance(getActivity());
        databaseHandler.displayRequestedBooks(current_userName, adapter, requestedBookList);

        SwipeRefreshLayout pullToRefresh = RequestedBookView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestedBookList.clear();
                adapter.notifyDataSetChanged();
                databaseHandler.displayRequestedBooks(current_userName, adapter, requestedBookList);
                pullToRefresh.setRefreshing(false);
            }
        });



        /**
         *Sets the onClickListener for each item in the Recycle View
         * and opens a book detail activity depending on if the clicked book
         * is owned by the current user or is owned by someone else.
         *
         * Initially created by tianxin3 and further developed by pling
         *
         * @see RequestedBookDetailsActivity
         * @see NonOwnerBookDetailsActivity
         */
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);

                Book clickedBook = requestedBookList.get(position);

                Context context = v.getContext();
                Intent intent = new Intent(context, RequestedBookDetailsActivity.class);
                String title = clickedBook.getTitle();
                String author = clickedBook.getAuthor();
                String isbn = clickedBook.getISBN();
                String pickUpLocation = clickedBook.getPickupLocation();
                String description = clickedBook.getDescription();

                Bundle extras = new Bundle();
                extras.putString("TITLE", title);
                extras.putString("AUTHOR", author);
                extras.putString("ISBN", isbn);
                extras.putString("PICKUPLOCATION", pickUpLocation);
                extras.putString("DESCRIPTION", description);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });




        return RequestedBookView;
    }

    @Override
    public void onStart(){
        super.onStart();
    }



}