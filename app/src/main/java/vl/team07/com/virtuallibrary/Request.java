/*
 * Request
 *
 * February 19, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;

/**
 * Created by MTX on 2019-02-24.
 */

public class Request {

    private User Requester;
    private Book RequestedBook;

    public Request(User requester, Book requestedBook){
        this.Requester = requester;
        this.RequestedBook = requestedBook;
        if (this.RequestedBook.getRequesters().contains(requester)){
            throw new IllegalArgumentException("The requester:" + requester.getName() + " has already requested " + requestedBook.getTitle());
        }
        else{
            this.RequestedBook.addRequester(requester);
        }
    }

    public void acceptRequest(){
        this.RequestedBook.setStatus(BookStatus.BORROWED);
    }

    public void acceptRequest(ArrayList<Book> requestedBooks){
        this.RequestedBook.setStatus(BookStatus.BORROWED);
        requestedBooks.add(this.RequestedBook);
    }

    public void rejectRequest(){
        this.RequestedBook.getRequesters().remove(this.Requester);
        this.RequestedBook.setStatus(BookStatus.AVAILABLE);
    }

    public ArrayList<User> showRequester(){
        return this.RequestedBook.getRequesters();
    }

    public void clearRequester(){
        this.RequestedBook.getRequesters().clear();
    }

    public void notifyBeRequest(){
        // Notification to owner if book is requested
    }

    public void notifyRequestAccepted(){
        // Notification to borrower if request is accepted
    }
}
