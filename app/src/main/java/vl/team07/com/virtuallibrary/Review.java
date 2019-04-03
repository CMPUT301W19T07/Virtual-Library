/*
 * Review Class
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The review data type
 * Hold a rating, comment, and reviwers username
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
 */
public class Review {

    private String ReviewerUsername;
    private Double Rating;
    private String Comment;
    private Book ReviewedBook;
    private User Reviewer;

    public Review(String reviewerUsername){
        this.ReviewerUsername = reviewerUsername;
    }

    public Review(){
    }

    public void setReviewer(String reviewer) {
        this.ReviewerUsername = reviewer;
    }

    /**
     * The Decimal format.
     */
    DecimalFormat decimalFormat = new DecimalFormat(".#");

    /**
     * Instantiates a new Review.
     *
     * @param reviewedBook the reviewed book
     */
    public Review(Book reviewedBook){
        this.ReviewedBook = reviewedBook;
    }

    /**
     * Instantiates a new Review.
     *
     * @param reviewedBook the reviewed book
     * @param reviewer     the reviewer
     */
    public Review(Book reviewedBook, User reviewer){
        this.ReviewedBook = reviewedBook;
        this.Reviewer = reviewer;
    }

    /**
     * Set reviewer.
     *
     * @param reviewer the reviewer
     */
    public void setReviewer(User reviewer){
        this.Reviewer = reviewer;
    }

    /**
     * Get reviewer string.
     *
     * @return the string
     */
    public String getReviewer(){
        return this.ReviewerUsername;
    }

    /**
     * Set rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating){
        if(rating >= 0.0 && rating <= 5.0){
            DecimalFormat decimalFormat = new DecimalFormat(".#");
            String result = decimalFormat.format(rating);
            this.Rating = Double.parseDouble(result);
        } else {
            throw new IllegalArgumentException("Illegal Rating.");
        }
    }

    /**
     * Get rating double.
     *
     * @return the double
     */
    public double getRating(){
        return this.Rating;
    }

    /**
     * Set comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment){
        this.Comment = comment;
    }

    /**
     * Get comment string.
     *
     * @return the string
     */
    public String getComment(){
        return this.Comment;
    }

    /**
     * Get average rating double.
     *
     * @param reviews the reviews
     * @return the double
     */
    public double getAverageRating(ArrayList<Review> reviews){
        int index = 0;
        double totalRating = 0.0;
        while (index < reviews.size()){
            totalRating += reviews.get(index).getRating();
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat(".#");
        String result = decimalFormat.format(totalRating / (double)reviews.size());
        return Double.parseDouble(result);
    }
}


