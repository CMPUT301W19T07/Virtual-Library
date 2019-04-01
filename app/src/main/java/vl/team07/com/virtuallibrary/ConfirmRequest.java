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
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class ConfirmRequest extends AppCompatActivity {
    private String result1;
    private String result2;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_request);
        result1 = getIntent().getExtras().getString("GiveObject");
        result2 = getIntent().getExtras().getString("position");
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

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        Context context = view.getContext();
        CharSequence text = "Request Accepted!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();

    }

    public void RejectRequest(View view){

        Intent returnIntent = new Intent();
        returnIntent.putExtra("PositionBack", result2);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }
}
