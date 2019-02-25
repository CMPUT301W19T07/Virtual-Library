/*
 * Book Information Test Class
 *
 * February 25, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

/*
Created by imtiazud on 02/25/2019
 */

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookInfoTest {

    private String bookTitle = "50 Shades of Grey";
    private String author = "E. L. James";
    private int ISBN = 1234567890;
    private String desc = "Fifty Shades of Grey is a 2011 erotic romancenovel";
    private String ssn = "50ShadesofGreyELJames1234567890FiftyShadesofGreyisa2011eroticromancenovel";

    private String username = "TestUsername";
    private String name = "TestName";
    private String email = "Test@example.com";
    private User owner = new User(username, name, email);

    private Book book = new Book(bookTitle, author, ISBN, owner, BookStatus.AVAILABLE, desc, ssn);
    private Review review = new Review(book, owner);

    @Test
    public void GetBookTitle() {
        assertEquals(bookTitle, book.getTitle());
    }

    @Test
    public void GetBookAuthor() {
        assertEquals(author, book.getAuthor());
    }

    @Test
    public void GetISBN() {
        assertEquals(ISBN, book.getISBN());
    }

    @Test
    public void GetDesc() {
        assertEquals(desc, book.getDescription());
    }

    @Test
    public void GetSSN() {
        assertEquals(ssn, book.getSearchString());
    }

    @Test
    public void GetReviewerName() {
        review.setReviewer(owner);

        String reviewer = review.getReviewer();

        assertEquals(name, reviewer);
    }

    @Test
    public void GetReviewRating() {
        review.setRating(4.5);
        double bookRatingByReview = review.getRating();
        double bookRating = 4.5;

        assertEquals(bookRating, bookRatingByReview, 0.001);
    }

    @Test
    public void GetAvgRating() {

        String username2 = "TestUsername2";
        String name2 = "TestName2";
        String email2 = "Test2@example.com";
        User owner2 = new User(username2, name2, email2);

        ArrayList<Review> reviewList = new ArrayList<>();
        Review review1 = new Review(book, owner);
        review1.setRating(5.0);
        Review review2 = new Review(book, owner2);
        review2.setRating(4.0);

        reviewList.add(review1);
        reviewList.add(review2);

        double avgRating = 4.5;
        double reviewAvgRating = review.getAverageRating(reviewList);

        assertEquals(avgRating, reviewAvgRating, 0.001);

    }

    @Test
    public void GetComment() {

        review.setComment("Wow! good book.");

        String comment = "Wow! good book.";
        String reviewComment = review.getComment();

        assertEquals(comment, reviewComment);
    }
}
