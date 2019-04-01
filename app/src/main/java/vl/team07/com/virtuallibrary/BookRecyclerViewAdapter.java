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

import android.content.ContentProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * The recycler view adapter used to display the list of books.
 *
 * Created by MTX on 2019-03-06.
 *
 * @version 1.0
 * @since 1.0
 */
public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookHolder>{

    public class BookHolder extends RecyclerView.ViewHolder{

        private TextView BookTitle, BookAuthor, BookOwner, BookStatus;
        private ImageView BookImage;

        public BookHolder(View itemView){
            super(itemView);
            BookTitle = itemView.findViewById(R.id.BookTitleText);
            BookAuthor = itemView.findViewById(R.id.BookAuthorText);
            BookOwner = itemView.findViewById(R.id.BookOwnerText);
            BookStatus = itemView.findViewById(R.id.BookStatusText);

            BookImage = itemView.findViewById(R.id.BookImage);
        }
        /**
         * Sets the details of a book into the view
         */
        public void setDetails(Book book){
            BookTitle.setText(book.getTitle());
            BookAuthor.setText(String.format(Locale.CANADA, "Author: %s", book.getAuthor()));
            BookOwner.setText(String.format(Locale.CANADA, "Owner: %s", book.getOwner()));
            BookStatus.setText(String.format(Locale.CANADA, "Status: %s", book.getStatus().toString()));


            //Loading the images from Firebase Storage
            Context context = itemView.getContext();
            DatabaseHandler dh = DatabaseHandler.getInstance(context);
            dh.retrieveImageFromFirebase(book.getISBN(), BookImage);
        }

    }


    private Context context;
    private ArrayList<Book> books;
    private View.OnClickListener onClickListener;

    /**
     * Collects data and current activity
     */
    public BookRecyclerViewAdapter(Context context, ArrayList<Book> books){
        this.context = context;
        this.books = books;
    }
    /**
     * Adds functions to the items in the list
     */
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card_view, viewGroup,false);
        BookHolder bookHolder = new BookHolder(view);

        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });

        return bookHolder;
    }
    /**
     * Adds Books to Recyclerview
     */
    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        Book book = books.get(i);
        bookHolder.setDetails(book);
    }
    /**
     * Returns item count
     */
    @Override
    public int getItemCount() {
        return books.size();
    }
    /**
     * Adds onclicklisnters to the items
     */
    public void setClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setBookList(ArrayList<Book> books){
        this.books = books;
    }

}
