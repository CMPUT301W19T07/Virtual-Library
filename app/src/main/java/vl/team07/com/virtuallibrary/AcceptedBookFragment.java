/*
 * Copyright <2019-1-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AcceptedBookFragment extends android.support.v4.app.Fragment {


    private RecyclerView recyclerView;
    private BookRecyclerViewAdapter adapter;
    private ArrayList<Book> acceptedBookList;

    SharedPreferences preferences;
    private DatabaseHandler databaseHandler;


    public AcceptedBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View AcceptedBookView = inflater.inflate(R.layout.fragment_accepted_book, container, false);

        recyclerView = (RecyclerView) AcceptedBookView.findViewById(R.id.AcceptedBookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        acceptedBookList = new ArrayList<>();
        adapter = new BookRecyclerViewAdapter(getContext(), acceptedBookList);
        recyclerView.setAdapter(adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(AcceptedBookView.getContext());
        String current_userName = preferences.getString("current_userName", "n/a");

        databaseHandler = DatabaseHandler.getInstance(getActivity());
        databaseHandler.displayAcceptedBooks(current_userName, adapter, acceptedBookList);


        adapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.indexOfChild(v);
                System.out.println("POSITION: "+position);

                Book clickedBook = acceptedBookList.get(position);

                Context context = v.getContext();
                Intent intent = new Intent(context, AcceptedBookDetailsActivity.class);
                String title = clickedBook.getTitle();
                String author = clickedBook.getAuthor();
                String isbn = clickedBook.getISBN();
                String pickUpLocation = clickedBook.getPickupLocation();
                String description = clickedBook.getDescription();
                String owner = clickedBook.getOwner();

                Bundle extras = new Bundle();
                extras.putString("TITLE", title);
                extras.putString("AUTHOR", author);
                extras.putString("ISBN", isbn);
                extras.putString("PICKUPLOCATION", pickUpLocation);
                extras.putString("DESCRIPTION", description);
                extras.putString("OWNER", owner);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });


        return AcceptedBookView;
    }




}