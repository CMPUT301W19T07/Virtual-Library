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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Comment;

/**
 * Activity that adds a review to a book
 *
 * @author Preston Ling
 * @version 1.0
 * @since 1.0
 */

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String title = extras.getString("TITLE");
        String author = extras.getString("AUTHOR");
        String isbn = extras.getString("ISBN");

        final Button AddReviewButton = findViewById(R.id.AddReviewButton);

        /**
         * OnClickListener for Add Review button. Checks if input within EditTexts are
         * valid. If valid, creates a new review.
         *
         * @see Review
         */
        AddReviewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText CommentEditText = findViewById(R.id.CommentEditText);
                EditText RatingEditText = findViewById(R.id.RatingEditText);

                boolean errorFlag = false;
                String comment = CommentEditText.getText().toString();
                if(comment.length() == 0){
                    CommentEditText.setError("Please enter a comment");
                    errorFlag = true;
                }
                String rating = RatingEditText.getText().toString();

                try {
                    Double ratingValue = Double.parseDouble(rating);
                    if(ratingValue < 0.0 || ratingValue > 5.0){
                        RatingEditText.setError("Enter Valid Rating");
                        errorFlag = true;
                    }
                }
                catch (Exception e){
                    RatingEditText.setError("Enter Valid Rating");
                    errorFlag = true;
                }

                if (errorFlag == false){
                    User user1 = new User("Testusername1", "Test name1", 0, "Test email", 0, "Canada", 0, "   ");
                    Book testBook = new Book(title, author, isbn, user1, BookStatus.AVAILABLE, "Description","SSN",null);
                    Review newReview = new Review(user1.getUserName());
                    newReview.setComment(comment);
                    newReview.setRating(Double.parseDouble(rating));

                    DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());
                    dh.addReview(testBook, newReview);

                    Context context = v.getContext();
                    CharSequence text = "Review Added!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();



                }


            }

        });
    }
}
