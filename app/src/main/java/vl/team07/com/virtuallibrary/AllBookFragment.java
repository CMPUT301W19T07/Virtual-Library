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
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private DatabaseHandler databaseHandler;


    private final String BOOK_PARENT = "Books";
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
        adapter = new BookRecyclerViewAdapter(getContext(), allBookList);
        recyclerView.setAdapter(adapter);


        databaseHandler = DatabaseHandler.getInstance(getActivity());

        TempList();
//        loadAllBook();
        databaseHandler.loadAllBook(allBookList,adapter);


        /**
         *Sets the onClickListener for each item in the Recycle View
         * and opens a book detail activity depending on if the clicked book
         * is owned by the current user or is owned by someone else.
         *
         * Initially created by tianxin3 and further developed by pling
         *
         * @see OwnerBookDetailsActivity
         * @see NonOwnerBookDetailsActivity
         */
        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);
                Book clickedBook = allBookList.get(position);

                Context context = v.getContext();
                if (clickedBook.getStatus() == BookStatus.AVAILABLE){
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
                else if(clickedBook.getStatus()== BookStatus.BORROWED){
                    Intent intent = new Intent(context, NonOwnerBookDetailsActivity.class);
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

            }
        });



        return AllBookView;
    }

    @Override
    public void onStart(){
        super.onStart();
    }


    // Temp for test
    public void TempList(){

        User user = new User("Test user", "Test name", 0, "Test email", 0, "Canada", 0, "");

        Book testBook = new Book("First Book", "First Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
        allBookList.add(testBook);
        testBook = new Book("Second Book", "Second Author", "1234567890", user, BookStatus.BORROWED, "Description","SSN",null);
        allBookList.add(testBook);
//        testBook = new Book("Third Book", "Third Author","1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Forth Book", "Forth Author", "1234567890", user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Fifth Book", "Fifth Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Sixth Book", "Sixth Author","1234567890", user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Seventh Book", "Seventh Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Eighth Book", "Eighth Author", "1234567890", user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Ninth Book", "Ninth Author", "1234567890", user, BookStatus.AVAILABLE, "Description","SSN",null);
//        allBookList.add(testBook);
//        testBook = new Book("Tenth Book", "Tenth Author", "1234567890", user, BookStatus.BORROWED, "Description","SSN",null);
//        allBookList.add(testBook);
    }


}