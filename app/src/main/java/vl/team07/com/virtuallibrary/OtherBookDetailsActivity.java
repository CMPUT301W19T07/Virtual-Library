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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OtherBookDetailsActivity extends AppCompatActivity {

    ArrayList<Review> reviewList = new ArrayList<Review>();
    String title;
    String author;
    String isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_book_details);

        //test Review List
        TempList();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String title = extras.getString("TITLE");
        String author = extras.getString("AUTHOR");
        String isbn = extras.getString("ISBN");
        String ownerAddress = extras.getString("OWNERADDRESS");
        String description = extras.getString("DESCRIPTION");

//        description = "WE need a long description in here. I should just try to practice my typin " +
//                "but his should be fine. Do we need it longer? Im not too sure, but hopefully i can get" +
//                "description long enough. What if it is longer? we need to test this quick," +
//                "will this be ok?";

        setTitle(title);

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
        final Button ViewCommentsButton = findViewById(R.id.ViewAllComments);
        final Button RequestButton = findViewById(R.id.RequestButton);


        User user1 = new User("Test user1", "Test name1", "0", "Test email", 0, "Canada", 0, "");
        Book testBook = new Book(title, author, isbn, user1, BookStatus.AVAILABLE, "Description","SSN",null);
        Review dummyReview = new Review(testBook, user1);

        //Setting appropriate text for text views
        bookTitleTextView.setText(title);
        authorTextView.setText("by " + author);
        ISBNTextView.setText("ISBN: "  +String.valueOf(isbn));
        DescriptionTextView.setText(description);
        TopReviewer1.setText("@"+ reviewList.get(0).getReviewer());
        TopReviewer2.setText("@" + reviewList.get(1).getReviewer());
        TopReviewer3.setText("@"+ reviewList.get(2).getReviewer());
        Reviewer1Comment.setText(reviewList.get(0).getComment());
        Reviewer2Comment.setText(reviewList.get(1).getComment());
        Reviewer3Comment.setText(reviewList.get(2).getComment());
        Reviewer1Rating.setText(String.valueOf(reviewList.get(0).getRating()));
        Reviewer2Rating.setText(String.valueOf(reviewList.get(1).getRating()));
        Reviewer3Rating.setText(String.valueOf(reviewList.get(2).getRating()));
        ReviewAverageScore.setText(String.valueOf(dummyReview.getAverageRating(reviewList)));


        RequestButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                CharSequence text = "Request Sent";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        ViewCommentsButton.setOnClickListener(new View.OnClickListener(){
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


    }
    public void TempList() {
        User user1 = new User("Testusername1", "Test name1", "0", "Test email", 0, "Canada", 0, "");
        Book testBook = new Book(title, author, isbn, user1, BookStatus.AVAILABLE, "Description", "SSN", null);
        Review testReview1 = new Review(testBook, user1);
        testReview1.setRating(4.9);
        testReview1.setComment("This is reviewer 1's comment");
        reviewList.add(testReview1);

        User user2 = new User("Testusername2", "Test name2", "0", "Test email", 0, "Canada", 0, "");
        Review testReview2 = new Review(testBook, user2);
        testReview2.setRating(4.4);
        testReview2.setComment("This is reviewer 2's comment");
        reviewList.add(testReview2);

        User user3 = new User("Testusername3", "Test name3", "0", "Test email", 0, "Canada", 0, "");
        Review testReview3 = new Review(testBook, user3);
        testReview3.setRating(4.7);
        testReview3.setComment("This is reviewer 3's comment");
        reviewList.add(testReview3);

    }
}
