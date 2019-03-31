/*
 * Class Name
 *
 * Date of Initiation
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Displays the book's data so the user can edit it, and then saves the data
 *
 * @version 1.0
 * @since 1.0
 */
public class EditBookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String title = extras.getString("TITLE");
        String author = extras.getString("AUTHOR");
        int isbn = extras.getInt("ISBN");
        String description = extras.getString("DESCRIPTION");

        TextView TitleView = (TextView) findViewById(R.id.addTitleView);
        TextView AuthorView = (TextView) findViewById(R.id.addAuthorView);
        TextView ISBNView = (TextView) findViewById(R.id.addISBNView);
        TextView DescriptionView = (TextView) findViewById(R.id.addDescriptionView);

        EditText TitleEdit = (EditText) findViewById(R.id.enterTitleView);
        EditText AuthorEdit = (EditText) findViewById(R.id.enterAuthorView);
        EditText ISBNEdit = (EditText) findViewById(R.id.enterISBNView);
        EditText DescriptionEdit = (EditText) findViewById(R.id.enterDescriptionView);

        Button editButton = (Button) findViewById(R.id.editButton);

        TitleEdit.setText(title);
        AuthorEdit.setText(author);
        ISBNEdit.setText(String.valueOf(isbn));
        DescriptionEdit.setText(description);

        editButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Saves changes to the database
             */
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                CharSequence text = "Applied Changes";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
