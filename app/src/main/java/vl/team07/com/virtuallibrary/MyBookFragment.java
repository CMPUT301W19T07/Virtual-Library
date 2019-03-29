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
import android.support.annotation.NonNull;
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

        /**
         *Sets the onClickListener for each item in the Recycle View
         * and opens a book detail activity that recognizes that the clicked
         * book is owned by the current user
         *
         * Initially created by tianxin3 and further developed by pling
         *
         * @see OwnerBookDetailsActivity
         *
         */
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);
                Book clickedBook = myBookList.get(position);

                Context context = v.getContext();
                Intent intent = new Intent(context, OwnerBookDetailsActivity.class);
                String title = clickedBook.getTitle();
                String author = clickedBook.getAuthor();
                String isbn = clickedBook.getISBN();
                String ownerAddress = clickedBook.getOwner().getAddress();
                String description = clickedBook.getDescription();

                Bundle extras = new Bundle();
                extras.putString("TITLE", title);
                extras.putString("AUTHOR", author);
                extras.putString("ISBN", isbn);
                extras.putString("OWNERADDRESS", ownerAddress);
                extras.putString("DESCRIPTION", description);
                intent.putExtras(extras);
                context.startActivity(intent);

            }
        });

        TempList();

        return MyBookView;
    }

    public void TempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Third Book", "Third Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Fifth Book", "Fifth Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Seventh Book", "Seventh Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Ninth Book", "Ninth Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);
        testBook = new Book("Eleventh Book", "Eleventh Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        myBookList.add(testBook);

        DatabaseHandler dh = DatabaseHandler.getInstance(getActivity());
        myBookList = dh.retrieveAvailableBook();

    }


}