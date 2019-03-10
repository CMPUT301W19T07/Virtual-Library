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

import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class NonOwnerBookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_owner_book_details);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String title = extras.getString("TITLE");
        String author = extras.getString("AUTHOR");
        int isbn = extras.getInt("ISBN");
        String ownerAddress = extras.getString("OWNERADDRESS");
        String description = extras.getString("DESCRIPTION");

        description = "WE need a long description in here. I should just try to practice my typin " +
                "but his should be fine. Do we need it longer? Im not too sure, but hopefully i can get" +
                "this shit done quick";
        final TextView bookTitleTextView = findViewById(R.id.BookTitleTextView);
        final TextView authorTextView = findViewById(R.id.AuthorTextView);
        final TextView ISBNTextView = findViewById(R.id.ISBNTextView);
        final TextView DescriptionTextView = findViewById(R.id.DescriptionTextView);
        final TextView OwnerAddressTextView = findViewById(R.id.OwnerAddressTextView);
        final TextView TopReviewsIndicator = findViewById(R.id.AverageReviewScore);

        bookTitleTextView.setText(title);
        authorTextView.setText("by " + author);
        ISBNTextView.setText("ISBN: "  +String.valueOf(isbn));
        DescriptionTextView.setText(description);
        OwnerAddressTextView.setText(ownerAddress);

    }

}
