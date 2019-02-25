/*
 * Copyright <2019-2-23> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    private Request request;
    private User Requester;
    private Book RequestedBook;
    private BookStatus Status;
    @Test
    public void testRequest(){
        Status = BookStatus.AVAILABLE;
        Requester = new User("ronghuishao", "ronghui", 123, "ronghui1@ualberta.ca", 21, "Canada", 780, "hub");
        RequestedBook = new Book("Times", "jr", 123, Requester, Status, "Good", "Time");
        request = new Request(Requester,RequestedBook);
        try {
            request = new Request(Requester,RequestedBook);
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            System.out.println("The requester:" + Requester.getName() + " has already requested " + RequestedBook.getTitle());
        }
    }

    @Test
    public void testAcceptRequest(){
        Status = BookStatus.AVAILABLE;
        Requester = new User("ronghuishao", "ronghui", 123, "ronghui1@ualberta.ca", 21, "Canada", 780, "hub");
        RequestedBook = new Book("Times", "jr", 123, Requester, Status, "Good", "Time");
        request = new Request(Requester,RequestedBook);
        request.acceptRequest();
        assertEquals(RequestedBook.getStatus(), Status.BORROWED);
    }

    @Test
    public void testRejectRequest(){
        Status = BookStatus.AVAILABLE;
        Requester = new User("ronghuishao", "ronghui", 123, "ronghui1@ualberta.ca", 21, "Canada", 780, "hub");
        RequestedBook = new Book("Times", "jr", 123, Requester, Status, "Good", "Time");
        request = new Request(Requester,RequestedBook);
        request.rejectRequest();
        assertEquals(RequestedBook.getStatus(), Status.AVAILABLE);
    }
}
