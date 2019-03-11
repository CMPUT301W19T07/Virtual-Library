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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class AllReviewsActivity extends AppCompatActivity {

    private ArrayList<Review> ReviewList = new ArrayList<>();
    String title;
    String author;
    int isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);
        initRecyclerView();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        title = extras.getString("TITLE");
        author = extras.getString("AUTHOR");
        isbn = extras.getInt("ISBN");
        TempList();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.ReviewRecyclerView);
        ReviewRecyclerViewAdapter adapter = new ReviewRecyclerViewAdapter(this, ReviewList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void TempList(){
        User user1 = new User("Testusername1", "Test name1", 0, "Test email", 0, "Canada", 0, "");
        Book testBook = new Book(title, author, isbn, user1, BookStatus.AVAILABLE, "Description","SSN",null);
        Review testReview1 = new Review(testBook, user1);
        testReview1.setRating(4.9);
        testReview1.setComment("This is reviewer 1's comment");
        ReviewList.add(testReview1);

        User user2 = new User("Testusername2", "Test name2", 0, "Test email", 0, "Canada", 0, "");
        Review testReview2 = new Review(testBook, user2);
        testReview2.setRating(4.4);
        testReview2.setComment("This is reviewer 2's comment");
        ReviewList.add(testReview2);

        User user3 = new User("Testusername3", "Test name3", 0, "Test email", 0, "Canada", 0, "");
        Review testReview3 = new Review(testBook, user3);
        testReview3.setRating(4.7);
        testReview3.setComment("This is reviewer 3's comment");
        ReviewList.add(testReview3);
    }
}
