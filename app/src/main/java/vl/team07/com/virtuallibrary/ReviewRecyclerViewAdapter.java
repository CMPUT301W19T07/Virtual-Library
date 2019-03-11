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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Recycler View Adapter for displaying all reviews of a book
 *
 * @author Preston Ling
 * @version 1.0
 * @since 1.0
 */
public class ReviewRecyclerViewAdapter extends
        RecyclerView.Adapter<ReviewRecyclerViewAdapter.ReviewHolder> {

    /**
     * Review Holder Class that initializes and sets the details of each review
     */
    public class ReviewHolder extends RecyclerView.ViewHolder{
        private TextView Reviewer, ReviewerRating, ReviewerComment;

        public ReviewHolder(View itemView){
            super(itemView);
            Reviewer = itemView.findViewById(R.id.ReviewerTextView);
            ReviewerRating = itemView.findViewById(R.id.RatingTextView);
            ReviewerComment = itemView.findViewById(R.id.CommentTextView);
        }

        public void setDetails(Review review){
            Reviewer.setText("@"+ review.getReviewer());
            ReviewerComment.setText(review.getComment());
            ReviewerRating.setText(String.valueOf(review.getRating()));
        }

    }

    private Context context;
    private ArrayList<Review> Reviews;

    public ReviewRecyclerViewAdapter(Context context, ArrayList<Review> reviews){
        this.context = context;
        this.Reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewRecyclerViewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_list_item, viewGroup,false);
        ReviewHolder reviewHolder = new ReviewHolder(view);

        return reviewHolder;
    }

    @Override
    public int getItemCount() {
        return Reviews.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewRecyclerViewAdapter.ReviewHolder reviewHolder, int i) {
        Review review = Reviews.get(i);
        reviewHolder.setDetails(review);
    }
}
