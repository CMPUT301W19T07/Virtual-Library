/*
 * Copyright <2019-2-22> <Ronghui Shao>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package vl.team07.com.virtuallibrary;

import java.util.ArrayList;

public class RequestProcessor {
    private Status status;
    private ArrayList<String> requestors;
    private String lender;

    public RequestProcessor(Status status) {
        this.status = status;
    }

    public RequestProcessor(Status status, ArrayList<String> requestors, String lender) {
        this.status = status;
        this.requestors = requestors;
        this.lender = lender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<String> getRequestors() {
        return requestors;
    }

    public void setRequestors(ArrayList<String> requestors) {
        this.requestors = requestors;
    }

    public void addRequestor(String user) {
        getRequestors().add(user);
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public void requestSent() {
        getStatus().setBookStatus("available");
    }

    public void returnBook() {
        getStatus().setBookStatus("available");
    }

    public void acceptRequest(String name) {
//        if (!getRequestors().contains(name)) {
            setLender(name);
            getRequestors().clear();
            getStatus().setBookStatus("borrowed");
//        }
    }

    public void rejectRequest(String name) {
//        if (!getRequestors().contains(name)) {
            getRequestors().remove(name);
            getStatus().setBookStatus("available");
//        }
    }
}
