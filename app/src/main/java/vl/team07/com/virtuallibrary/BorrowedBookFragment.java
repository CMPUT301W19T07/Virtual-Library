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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class BorrowedBookFragment extends android.support.v4.app.Fragment {


    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> borrowedBookList;


    public BorrowedBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View BorrowedBookView = inflater.inflate(R.layout.fragment_borrowed_book, container, false);

        recyclerView = (RecyclerView) BorrowedBookView.findViewById(R.id.BorrowedBookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        borrowedBookList = new ArrayList<>();
        adapter = new BookRecyclerViewAdapter(getContext(), borrowedBookList);
        recyclerView.setAdapter(adapter);


        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);

                Book clickedBook = borrowedBookList.get(position);

                Context context = v.getContext();
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
        });

        TempList();


        return BorrowedBookView;
    }


    public void TempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook;
        testBook = new Book("Second Book", "Second Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        borrowedBookList.add(testBook);
        testBook = new Book("Forth Book", "Forth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        borrowedBookList.add(testBook);
        testBook = new Book("Sixth Book", "Sixth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        borrowedBookList.add(testBook);
        testBook = new Book("Eighth Book", "Eighth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        borrowedBookList.add(testBook);
        testBook = new Book("Tenth Book", "Tenth Author", 1234567890, user, BookStatus.BORROWED, "Description","SSN",null);
        borrowedBookList.add(testBook);
    }
}