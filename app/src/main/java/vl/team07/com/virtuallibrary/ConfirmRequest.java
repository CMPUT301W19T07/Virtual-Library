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
import android.net.Uri;
import android.support.annotation.NonNull;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;


public class ConfirmRequest extends AppCompatActivity {

    int PLACE_PICKER_REQUEST = 1;
    private String locationPicked;

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

    SharedPreferences preferences;
    private DatabaseHandler databaseHandler;

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
        Button selectPickupLocationButton = (Button) findViewById(R.id.selectPickupLocation);

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

        selectPickupLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPointIntent = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(pickPointIntent, PICK_MAP_POINT_REQUEST);
            }
        });

    }

    static final int PICK_MAP_POINT_REQUEST = 999;
    public void AcceptRequest(View view){

//        Intent intent = new Intent(ConfirmRequest.this, MapsActivity.class);
//        startActivityForResult(intent, PLACE_PICKER_REQUEST);

//        Uri navigationIntentUri = Uri.parse("google.navigation:q=" + 12f +"," + 2f);//creating intent with latlng
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);
        if(pickupLocation.equals("To Be Determined")){
            Toast.makeText(this, "Please Choose a Pickup Location Before Accepting", Toast.LENGTH_LONG).show();
        }
        else {
            preferences = PreferenceManager.getDefaultSharedPreferences(ConfirmRequest.this);
            String current_userName = preferences.getString("current_userName", "n/a");

            DatabaseHandler dh = DatabaseHandler.getInstance(ConfirmRequest.this);

            // Updating the pickup location in the database
            //dh.updatePickUpLocation(latLng.latitude, latLng.longitude, book);
            //Updating the pickup location of the book manually so acceptRequest has the updated copy
            //book.setPickupLocation(String.valueOf(latLng.latitude) + " " + String.valueOf(latLng.longitude));

            book.setStatus(BookStatus.ACCEPTED);

            dh.acceptRequest(book, request.getRequesterUsername(), current_userName);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    public void RejectRequest(View view){

        DatabaseHandler dh = DatabaseHandler.getInstance(ConfirmRequest.this);
        dh.deleteRequest(book, request.getRequesterUsername() );

        Intent returnIntent = new Intent();
        returnIntent.putExtra("PositionBack", result2);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_MAP_POINT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                LatLng latLng = (LatLng) data.getParcelableExtra("picked_point");
                pickupLocation = Double.toString(latLng.latitude) + " " + Double.toString(latLng.longitude);
                Toast.makeText(this, "Point Chosen: " + latLng.latitude + " " + latLng.longitude, Toast.LENGTH_LONG).show();


                // Updating the pickup location in the database
                //dh.updatePickUpLocation(latLng.latitude, latLng.longitude, book);
                //Updating the pickup location of the book manually so acceptRequest has the updated copy
                //book.setPickupLocation(String.valueOf(latLng.latitude) + " " + String.valueOf(latLng.longitude));

                book.setStatus(BookStatus.ACCEPTED);

            }
        }
    }


}
