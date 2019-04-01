/*
 * AcceptedBookDetailsActivity
 *
 * April 1, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 *
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AcceptedBookDetailsActivity extends AppCompatActivity {

    ArrayList<Review> reviewList = new ArrayList<Review>();
    String title;
    String author;
    String isbn;
    String description;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference =  database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_book_details);

        //TODO intent extras from accepted recycler view


        //Getting text views from activity
        final TextView bookTitleTextView = findViewById(R.id.BookTitleTextView);
        final TextView authorTextView = findViewById(R.id.AuthorTextView);
        final TextView ISBNTextView = findViewById(R.id.ISBNTextView);
        final TextView DescriptionTextView = findViewById(R.id.DescriptionTextView);
        DescriptionTextView.setMovementMethod(new ScrollingMovementMethod());
        final TextView ReviewAverageScore = findViewById(R.id.AverageReviewScore);
        final TextView TopReviewer1 = findViewById(R.id.TopReviewUser1);
        final TextView TopReviewer2 = findViewById(R.id.TopReviewUser2);
        final TextView TopReviewer3 = findViewById(R.id.TopReviewUser3);
        final TextView Reviewer1Comment = findViewById(R.id.User1Comment);
        final TextView Reviewer2Comment = findViewById(R.id.User2Comment);
        final TextView Reviewer3Comment = findViewById(R.id.User3Comment);
        final TextView Reviewer1Rating = findViewById(R.id.User1Rating);
        final TextView Reviewer2Rating = findViewById(R.id.User2Rating);
        final TextView Reviewer3Rating = findViewById(R.id.User3Rating);
        final Button locationButton = findViewById(R.id.locationButton);
        final Button recieveBook = findViewById(R.id.recieveButton);
        final Button ViewCommentsButton = findViewById(R.id.ViewAllComments);

        User user1 = new User("Test user1", "Test name1", "0", "Test email", 0, "Canada", 0, "");
        Book testBook = new Book(title, author, isbn, "Test user1", BookStatus.AVAILABLE, "Description", "SSN", null);
        Review dummyReview = new Review(user1.getUserName());


        DatabaseReference reviewReference = databaseReference.child("Reviews");
        reviewReference.child(isbn).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Review> currentReviewList = new ArrayList<Review>();
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Review review = adSnapshot.getValue(Review.class);
                    System.out.println("Comment of review is "+ review.getComment());
                    currentReviewList.add(review);
                }

                System.out.println("Size of the list in onDataChange is: " + currentReviewList.size());
                ReviewAverageScore.setText(String.valueOf(dummyReview.getAverageRating(currentReviewList)));

                if(currentReviewList.size() >= 1){
                    TopReviewer1.setText("@"+ currentReviewList.get(0).getReviewer());
                    Reviewer1Comment.setText(currentReviewList.get(0).getComment());
                    Reviewer1Rating.setText(String.valueOf(currentReviewList.get(0).getRating()));
                }

                if(currentReviewList.size() >= 2){
                    TopReviewer2.setText("@" + currentReviewList.get(1).getReviewer());
                    Reviewer2Comment.setText(currentReviewList.get(1).getComment());
                    Reviewer2Rating.setText(String.valueOf(currentReviewList.get(1).getRating()));
                }
                if(currentReviewList.size() >= 3){
                    TopReviewer3.setText("@"+ currentReviewList.get(2).getReviewer());
                    Reviewer3Comment.setText(currentReviewList.get(2).getComment());
                    Reviewer3Rating.setText(String.valueOf(currentReviewList.get(2).getRating()));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Looking at all the reviews
        ViewCommentsButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                Intent intent = new Intent(context, AllReviewsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("TITLE", title);
                extras.putString("AUTHOR", author);
                extras.putString("ISBN", isbn);
                extras.putString("DESCRIPTION", description);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        locationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHandler dh = DatabaseHandler.getInstance(AcceptedBookDetailsActivity.this);
                Context context = v.getContext();
                dh.navToPickUpLocation(testBook, context);

            }
        });

    }
}
