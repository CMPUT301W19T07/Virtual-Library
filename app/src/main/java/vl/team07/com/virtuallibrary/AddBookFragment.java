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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;


public class AddBookFragment extends android.support.v4.app.Fragment {


    private TextView TitleView, AuthorView, ISBNView, DescriptionView;
    private EditText TitleEdit, AuthorEdit, ISBNEdit, DescriptionEdit;
    private Button addButton;
    private Book book;
    private Gson gson;
    private final String BOOK_PARENT = "All Books";

    private ArrayList<Book> allBooks = new ArrayList<>();
//    private ArrayAdapter<Book> adapter = new ArrayAdapter<Book>();
    private String title, author, description;
    private int ISBN;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View AddBookView =  inflater.inflate(R.layout.fragment_add_book, container, false);

        getActivity().setTitle("Add Book");

        TitleView = (TextView) AddBookView.findViewById(R.id.addTitleView);
        AuthorView = (TextView) AddBookView.findViewById(R.id.addAuthorView);
        ISBNView = (TextView) AddBookView.findViewById(R.id.addISBNView);
        DescriptionView = (TextView) AddBookView.findViewById(R.id.addDescriptionView);

        TitleEdit = (EditText) AddBookView.findViewById(R.id.enterTitleView);
        AuthorEdit = (EditText) AddBookView.findViewById(R.id.enterAuthorView);
        ISBNEdit = (EditText) AddBookView.findViewById(R.id.enterISBNView);
        DescriptionEdit = (EditText) AddBookView.findViewById(R.id.enterDescriptionView);

        addButton = (Button) AddBookView.findViewById(R.id.addButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        return AddBookView;
    }

    public void onStart(){
        super.onStart();


        // Put book to 'My Books' Fragment by click ADD button
        // Will be update use firebase later
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addBook();
//                title = TitleEdit.getText().toString();
//                System.out.println("TITLE: "+ title);
//                int ISBN;
//                // Set new book
//
//                author = AuthorEdit.getText().toString();
//                description = DescriptionEdit.getText().toString();
//
//                try {
//                    ISBN = Integer.parseInt(ISBNEdit.getText().toString());
//                }catch (NumberFormatException e){
//                    ISBN = 0;
//                }
//
//                book = new Book();
//                book.setTitle(title);
//                book.setAuthor(author);
//                book.setDescription(description);
//                book.setISBN(ISBN);


//
//                gson = new Gson();
//                String newBook = gson.toJson(book);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Add Book", newBook);
//                setArguments(bundle);


//                databaseReference.child(BOOK_PARENT).child(book.getStatus().toString()).child(Integer.toString(book.getISBN())).setValue(book);
            }
        });


    }


    public void addBook(){

        title = TitleEdit.getText().toString();
        author = AuthorEdit.getText().toString();
        ISBN = Integer.parseInt(ISBNEdit.getText().toString());
        description = DescriptionEdit.getText().toString();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(BOOK_PARENT).child(BookStatus.AVAILABLE.toString()).child(ISBNEdit.getText().toString()).exists()){
                    System.out.println("ISBN HAS ALREADY EXISTED");
                    alertDialog();
                }else if(dataSnapshot.child(BOOK_PARENT).child(BookStatus.BORROWED.toString()).child(ISBNEdit.getText().toString()).exists()){
                    alertDialog();
                }else{
                    book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setISBN(ISBN);
                    book.setDescription(description);
                    databaseReference.child(BOOK_PARENT).child(book.getStatus().toString()).child(Integer.toString(book.getISBN())).setValue(book);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Error");
        builder.setMessage("The book has already exists in the Firebase.");
        builder.setCancelable(true);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

}
