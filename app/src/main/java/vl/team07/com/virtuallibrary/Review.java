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
 * Created by MTX on 2019-02-24.
 */

public class Review {

    private User Reviewer;
    private double Rating;
    private String Comment;
    private Book ReviewedBook;

    DecimalFormat decimalFormat = new DecimalFormat(".#");

    public Review(Book reviewedBook){
        this.ReviewedBook = reviewedBook;
    }

    public Review(Book reviewedBook, User reviewer){
        this.ReviewedBook = reviewedBook;
        this.Reviewer = reviewer;
    }

    public void setReviewer(User reviewer){
        this.Reviewer = reviewer;
    }

    public String getReviewer(){
        return this.Reviewer.getName();
    }
    public void setRating(double rating){
        if(rating >= 0.0 && rating <= 5.0){
            String result = decimalFormat.format(rating);
            this.Rating = Double.parseDouble(result);
        } else {
            throw new IllegalArgumentException("Illegal Rating.");
        }
    }

    public double getRating(){
        return this.Rating;
    }

    public void setComment(String comment){
        this.Comment = comment;
    }

    public String getComment(){
        return this.Comment;
    }

    public double getAverageRating(ArrayList<Review> reviews){
        int index = 0;
        double totalRating = 0.0;
        while (index < reviews.size()){
            totalRating += reviews.get(index).getRating();
            index++;
        }
        String result = decimalFormat.format(totalRating / (double)reviews.size());
        return Double.parseDouble(result);
    }
}

