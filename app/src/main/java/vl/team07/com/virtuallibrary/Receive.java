/*
 * Recieve
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

/**
 * Handles the reciving of new books
 *
 * Created by MTX on 2019-02-24.
 *
 * @version 1.0
 * @since 1.0
 */

public class Receive {

    private User Borrower;
    private Book BorrowedBook;
    /**
     *Add borrowed book to user
     */
    public Receive(User borrower, Book borrowedBook){
        this.Borrower = borrower;
        this.BorrowedBook = borrowedBook;
    }
    /**
     *Updates status
     */
    public void confirmReceive(){
        this.BorrowedBook.setStatus(BookStatus.AVAILABLE);
    }
}
