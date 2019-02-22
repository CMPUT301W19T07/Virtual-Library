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

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RequestProcessorTest {
    private RequestProcessor requestProcessor;
    private Status status;

    @Test
    public void testLendBook(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        requestProcessor.getStatus().setBookStatus("available");
        requestProcessor.lendBook();
        assertEquals(requestProcessor.getStatus().getBookStatus(), "available");
    }

    @Test
    public void testConfirmBorrowed(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        requestProcessor.getStatus().setBookStatus("available");
        requestProcessor.confirmBorrowed();
        assertEquals(requestProcessor.getStatus().getBookStatus(), "borrowed");
    }

    @Test
    public void testReturnBook(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        requestProcessor.getStatus().setBookStatus("borrowed");
        requestProcessor.returnBook();
        assertEquals(requestProcessor.getStatus().getBookStatus(), "borrowed");
    }

    @Test
    public void testConfirmReturned(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        requestProcessor.getStatus().setBookStatus("borrowed");
        requestProcessor.confirmReturned();
        assertEquals(requestProcessor.getStatus().getBookStatus(), "available");
    }

    @Test
    public void testAddRequestor(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        ArrayList<String> requestors = new ArrayList<>();
        requestProcessor.setRequestors(requestors);
        String requestor = "ronghui1";
        requestProcessor.addRequestor(requestor);
        assertTrue(requestProcessor.getRequestors().contains(requestor));
    }

    @Test
    public void testAcceptRequest(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        ArrayList<String> requestors = new ArrayList<>();
        requestProcessor.setRequestors(requestors);
        String Requestor1 = "ronghui1";
        String Requestor2 = "ronghui2";
        requestProcessor.addRequestor(Requestor1);
        requestProcessor.addRequestor(Requestor2);
        assertEquals(requestProcessor.getRequestors().size(),2);
        requestProcessor.acceptRequest(Requestor1);
        assertEquals(requestProcessor.getRequestors().size(),0);
        assertEquals(requestProcessor.getLender(),Requestor1);
        assertEquals(requestProcessor.getStatus().getBookStatus(),"borrowed");

    }

    @Test
    public void testDenyRequestor(){
        status = new Status();
        requestProcessor = new RequestProcessor(status);
        ArrayList<String> requestors = new ArrayList<>();
        requestProcessor.setRequestors(requestors);
        String Requestor1 = "ronghui1";
        String Requestor2 = "ronghui2";
        requestProcessor.addRequestor(Requestor1);
        requestProcessor.addRequestor(Requestor2);
        assertEquals(requestProcessor.getRequestors().size(),2);
        requestProcessor.rejectRequest(Requestor1);
        assertEquals(requestProcessor.getRequestors().size(),1);
        assertEquals(requestProcessor.getRequestors().get(0),Requestor2);
        assertEquals(requestProcessor.getStatus().getBookStatus(),"available");
    }

}
