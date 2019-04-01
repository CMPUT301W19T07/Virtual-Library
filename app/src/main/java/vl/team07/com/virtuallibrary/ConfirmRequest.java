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

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class ConfirmRequest extends AppCompatActivity {
    private String result1;
    private String result2;
    private Request request;

    String title;
    String author;
    private String status;
    String isbn;
    String owner;
    String pickupLocation;
    String description;

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_request);
        result1 = getIntent().getExtras().getString("GiveObject");
        result2 = getIntent().getExtras().getString("position");

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        title = extras.getString("TITLE");
        author = extras.getString("AUTHOR");
        isbn = extras.getString("ISBN");
        pickupLocation = extras.getString("PICKUPLOCATION");
        description = extras.getString("DESCRIPTION");
        status = extras.getString("STATUS");
        owner = extras.getString("OWNER");

        System.out.println("ISBN of book is : " + isbn);

        book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setISBN(isbn);
        book.setOwner(owner);
        book.setStatus(BookStatus.AVAILABLE);
        book.setDescription(description);
        book.setPickupLocation(pickupLocation);


        Gson gson = new Gson();
        request = gson.fromJson(result1, Request.class);

        TextView bookTitleText = (TextView) findViewById(R.id.bookTitle);
        TextView UsernameText = (TextView) findViewById(R.id.Username);
        TextView EmailText = (TextView) findViewById(R.id.Email);
        TextView AddressText = (TextView) findViewById(R.id.Address);

        if (bookTitleText != null) {
            bookTitleText.setText(request.getRequestedBookTitle());
        }

        if (UsernameText != null) {
            UsernameText.setText(request.getRequesterUsername());
        }

        if (EmailText != null) {
            EmailText.setText(request.getRequesterEmail());
        }

        if (AddressText != null) {
            AddressText.setText(request.getRequesterAddress());
        }

    }

    public void AcceptRequest(View view){

        DatabaseHandler dh = DatabaseHandler.getInstance(ConfirmRequest.this);
        dh.acceptRequest(book, request.getRequesterUsername() );

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);

        finish();

    }

    public void RejectRequest(View view){

        DatabaseHandler dh = DatabaseHandler.getInstance(ConfirmRequest.this);
        dh.deleteRequest(request.getRequestedBookISBN(), request.getRequesterUsername() );

        Intent returnIntent = new Intent();
        returnIntent.putExtra("PositionBack", result2);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }

}
