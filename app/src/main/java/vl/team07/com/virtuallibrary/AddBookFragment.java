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

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;
import java.util.ArrayList;


public class AddBookFragment extends android.support.v4.app.Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;

    private TextView TitleView, AuthorView, ISBNView, DescriptionView;
    private EditText TitleEdit, AuthorEdit, ISBNEdit, DescriptionEdit;
    private Button addButton;
    private Book book;
    private Gson gson;
    private final String BOOK_PARENT = "All Books";

    private ArrayList<Book> allBooks = new ArrayList<>();
//    private ArrayAdapter<Book> adapter = new ArrayAdapter<Book>();
    private String title, author, description, ISBN;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private ImageView imageView;



    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View AddBookView =  inflater.inflate(R.layout.fragment_add_book, container, false);

        getActivity().setTitle("Add Book");

        TitleView = (TextView) AddBookView.findViewById(R.id.addTitleView);
        AuthorView = (TextView) AddBookView.findViewById(R.id.addAuthorView);
        ISBNView = (TextView) AddBookView.findViewById(R.id.addISBNView);
        DescriptionView = (TextView) AddBookView.findViewById(R.id.addDescriptionView);

        TitleEdit = (EditText) AddBookView.findViewById(R.id.enterTitleView);
        AuthorEdit = (EditText) AddBookView.findViewById(R.id.enterAuthorView);
        ISBNEdit = (EditText) AddBookView.findViewById(R.id.enterISBNView);
        DescriptionEdit = (EditText) AddBookView.findViewById(R.id.enterDescriptionView);
        imageView = AddBookView.findViewById(R.id.imageView);

        addButton = (Button) AddBookView.findViewById(R.id.addButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        return AddBookView;
    }

    /**
     * Starting the activity of the gallery, to pick the book image
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageView.setImageURI(selectedImage);

        }
    }
    
    public void onStart() {
        super.onStart();

        if(MainActivity.SCAN_ISBN != null){
            this.ISBN = MainActivity.SCAN_ISBN;

            GoogleBookAPI googleBookAPI = new GoogleBookAPI(new AsyncResponse() {
                @Override
                public void processFinish(JSONObject output) {
                    try {
                        title = output.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
                        author = output.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("authors");
                        description = output.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");

                        if(title!=null){
                            TitleEdit.setText(title);
                        }
                        if(author!=null){
                            author = author.substring(1,author.length()-1);
                            String[] authors = author.split(",");
                            author = authors[0].substring(1,authors[0].length()-1);
                            if(authors.length > 1){
                                for(int i=1;i<authors.length;i++){
                                    String author_new = authors[i].substring(1,authors[i].length()-1);
                                    author = author + ", " + author_new;
                                }
                            }
                            AuthorEdit.setText(author);
                        }
                        if(description!=null){
                            DescriptionEdit.setText(description);
                        }
                        ISBNEdit.setText(ISBN);
                    }catch (JSONException e){
                        return;
                    }
                }
            });
            googleBookAPI.execute(this.ISBN);

        }



        // Put book to 'My Books' Fragment by click ADD button
        // Will be update use firebase later
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Set new book

                title = TitleEdit.getText().toString();
                author = AuthorEdit.getText().toString();
                description = DescriptionEdit.getText().toString();
                ISBN = ISBNEdit.getText().toString();



                /**
                 * Getting the image uploaded and storing it in book data
                 */
                Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                if (bmp != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setDescription(description);
                    book.setISBN(ISBN);
                    book.setImage(byteArray);

                } else {
                    book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setDescription(description);
                    book.setISBN(ISBN);
                }

                DatabaseHandler dh = new DatabaseHandler(getActivity());
                dh.addBook(book);

            }
        });

        /**
         * Adding an image from gallery once the imageView is clicked. It will launch the gallery,
         * and allow the user to select an upload a photo.
         */
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
    }

}
