/*
 * DatabaseHandler
 *
 * March 01, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */


package vl.team07.com.virtuallibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Firebase Database Handler. This class is designed to handle all the tasks that
 * are related to the Firebase Realtime Database. The TAG is gotten by getting the class name where
 * DatabaseHandler is instantiated and is converted to a simple string.
 *
 * @author Imtiaz Raqib
 * @version 1.0
 * @since 1.0
 */

public class DatabaseHandler {


    private static DatabaseHandler instance;
    private final String TAG = getClass().getSimpleName();
    private FirebaseDatabase myDatabase;
    private DatabaseReference databaseReference;

    private FirebaseStorage myDbStorage;
    private StorageReference myDbStorageRef;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private Context context;

    private ArrayList<Book> newBookList = new ArrayList<>();
    private ArrayList<Review> newReviewList = new ArrayList<>();

    private final String BOOK_PARENT = "Books";
    private final String BOOK_AVAILABLE = BookStatus.AVAILABLE.toString();
    private final String BOOK_BORROWED = BookStatus.BORROWED.toString();




    /**
     * Constructor for the DatabaseHandler class which takes the context of which class it is in
     * when instantiated.
     *
     * @param context
     */
    private DatabaseHandler(Context context) {
        firebaseAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance();
        databaseReference = myDatabase.getReference();
        firebaseUser = firebaseAuth.getCurrentUser();

        this.context = context;
    }

    public static DatabaseHandler getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    /**
     * This method adds a book to the database using the ISBN as unique child key.
     *
     * @param book
     * @see AddBookFragment
     */
    public void addBook(final Book book) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String title = "Error";
                final String message = "The book has already exist in the library!";

                if(dataSnapshot.child(BOOK_PARENT).child(book.getISBN()).exists()) {
                    createToast("This book already exists");
                }else{
                    databaseReference.child("Books").child(book.getISBN()).setValue(book);

                    showToast("The book is added!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//
//        databaseReference.child("Books").child(String.valueOf(book.getISBN())).setValue(book);
//
//        Toast toast = Toast.makeText(this.context, "Book is added!", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
//        toast.show();
    }

    /**
     * This method retrieves all the books from the database under child Books and returns it as
     * an arraylist of type Boook
     *
     * @return ArrayList
     * @see MyBookFragment
     */

    public ArrayList<Book> retrieveAvailableBook() {

        databaseReference.keepSynced(true);
        databaseReference.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Book book = adSnapshot.getValue(Book.class);
                    newBookList.add(book);
                }
                System.out.println("Size of the list in onDataChange is: " + newBookList.size());
                DatabaseHandler db = getInstance(context);
                db.setNewBookList(newBookList);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("Size of the list outside onDataChange is: " + newBookList.size());
        System.out.println("Title of the third book is " + newBookList.get(2));
        return newBookList;
    }


    /**
     * Add newArrayList to database's newBookList
     */

    public void setNewBookList(ArrayList<Book> bookList){
        this.newBookList = bookList;
    }

    /**
     * This method retrieves all the books from the database
     * @param ISBN
     * @return
     */

    public ArrayList<Review> retrieveBookReviews(String ISBN) {

        databaseReference.keepSynced(true);
        DatabaseReference reviewref = databaseReference.child("Reviews");
        reviewref.child(ISBN).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Review> currentReviewList = new ArrayList<Review>();
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Review review = adSnapshot.getValue(Review.class);
                    System.out.println("Comment of review is "+ review.getComment());
                    currentReviewList.add(review);
                }
                System.out.println("Size of the list in onDataChange is: " + currentReviewList.size());
                DatabaseHandler db = getInstance(context);
                db.setReviewList(currentReviewList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println("Size of the list outside onDataChange is: " + newReviewList.size());
        return this.newReviewList;
    }


    /**
     * This method sets all the reviews for a book in a list.
     * @param reviewList
     */
    public void setReviewList(ArrayList<Review> reviewList){
        this.newReviewList = reviewList;
    }


    /**
     * This method takes in a User object and adds it to the database using the values provided
     * during the signUp process
     *
     * @param user
     * @see SignUp
     */
    public void addUser(User user) {
        databaseReference.child("Users").child(user.getUserName()).setValue(user);
    }

    /**
     * This method takes in a book object and a review object to add a review to the database under
     * the ISBN of the of clicked book
     *
     * @param book
     * @param review
     * @see AddReviewActivity
     */
    public void addReview (Book book, Review review) {
        databaseReference.child("Reviews").child(String.valueOf(book.getISBN())).child(review.getReviewer()).setValue(review);
    }


    /**
     * This method takes in a book array list and a book recyclerview adapter to load book from firebase
     *
     * @param allBookList
     * @param adapter
     * @see AllBookFragment
     */
    public void loadAllBook(ArrayList<Book> allBookList, BookRecyclerViewAdapter adapter){
        databaseReference.keepSynced(true);
        databaseReference.child(BOOK_PARENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Book availableBook;
                    availableBook = data.getValue(Book.class);

                    if(availableBook!=null){
                        allBookList.add(availableBook);
                    }
                }
//                adapter.setBookList(allBookList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * This method loads
     * @param myBookList
     * @param adapter
     */

    public void loadBooksIntoRecyclerView(ArrayList<Book> myBookList, BookRecyclerViewAdapter adapter){
        databaseReference.keepSynced(true);
        databaseReference.child(BOOK_PARENT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Book availableBook;
                    availableBook = data.getValue(Book.class);

                    if(availableBook!=null){
                        myBookList.add(availableBook);
                    }
                }
//                adapter.setBookList(allBookList);
                adapter.notifyDataSetChanged();
                System.out.println("Size of myBookList after adapter update: "+ myBookList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void displayOwnedBooks(String current_userName ,BookRecyclerViewAdapter adapter, ArrayList<Book> myBookList){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> bookList = user.getOwnedBookList();
                System.out.println("Size of the Book list is: " + bookList.size());
                for(Book book : bookList){
                    myBookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void loadReviews(ArrayList<Review> reviewList, ReviewRecyclerViewAdapter adapter, String ISBN){
        databaseReference.keepSynced(true);
        DatabaseReference reviewref = databaseReference.child("Reviews");
        reviewref.child(ISBN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    Review review = data.getValue(Review.class);

                    if(review!=null){
                        reviewList.add(review);
                    }
                }
//                adapter.setBookList(allBookList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    /**
     * This method is takes in title string and message string to give alert dialog
     * @param title
     * @param message
     */
    public void alertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public void showToast(String message){

        Toast toast = Toast.makeText(this.context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
    }

    /**
     * This method takes in the latitude and longitude selected by the book owner when he has
     * accepted the request of a book
     *
     * @param latitude
     * @param longitude
     * @param book
     */

    public void updatePickUpLocation(double latitude, double longitude, Book book) {
        DatabaseReference bookRef = databaseReference.child("Books").child(book.getISBN());
        bookRef.child("PickupLocation").setValue(String.valueOf(latitude) + " " +  String.valueOf(longitude));
    }

    public void navToPickUpLocation(Book book, Context context) {
        DatabaseReference bookRef = databaseReference.child("Books").child(book.getISBN())
                .child("pickupLocation");
        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] latLang = dataSnapshot.getValue().toString().split(" ");
                double latitude = Double.parseDouble(latLang[0]);
                double longitude = Double.parseDouble((latLang[1]));

                Uri navigationIntentUri = Uri.parse("google.navigation:q=" + latitude +"," + longitude);//creating intent with latlng
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });


    }

    /**
     * This method will take the images from the firebase storage and set the image in various
     * activities.
     * @param ISBN
     * @param bookCover
     */

    public void retrieveImageFromFirebase(String ISBN, ImageView bookCover) {
        myDbStorage = FirebaseStorage.getInstance();
        myDbStorageRef = myDbStorage.
                getReferenceFromUrl("gs://virtuallibrary-12090.appspot.com/").
                child("imagesBook/"+ ISBN + ".png");

//        myDbStorageRef.child("images/"+ISBN+".png").getDownloadUrl().
//                addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        bookCover.setImageURI(uri);
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                createToast("Failed to load image");
//            }
//        });

        try {
            final File localFile = File.createTempFile("images", "png");
            myDbStorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    bookCover.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {}
    }

    /**
     * This method takes the image uploaded while adding a book to the Firebase storage. It then
     * takes the URL related to the stored image and stores it in the Book Database
     *
     * @param bmp
     * @param book
     * @see AddBookFragment
     */

    public void uploadImageToFirebase (Bitmap bmp, Book book) {
        myDbStorage = FirebaseStorage.getInstance();
        myDbStorageRef = myDbStorage.getReference();

        final StorageReference ImagesRef = myDbStorageRef.child("imagesBook/"+book.getISBN()+".png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        final UploadTask uploadTask = ImagesRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                createToast("Failed to add image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.i("problem", task.getException().toString());
                        }

                        return ImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri imageURI = task.getResult();
//                            DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference()
//                                    .child("Books").child(String.valueOf(book.getISBN()));
//
//                            bookRef.child("image").setValue(imageURI.toString());
                        }
                        else {
                            createToast("Failed!");
                        }
                    }
                });
            }
        });

    }

    /**
     * This method adds the current username to a SharedPreferences file. This helps us keep track
     * of the current user
     * @param email
     * @param preferences
     * @param edit
     */

    public void getUsernameToPref (String email, SharedPreferences preferences, SharedPreferences.Editor edit) {
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);
                    System.out.println("Gotten username is : "+ user.getUserName());
                    if (email.equals(user.getEmail())){
                        edit.putString("current_userName", user.getUserName());
                        edit.commit();

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /**
     * This method retrieves the use profile picture from the firebase storage.
     * @param userName
     * @param userPicture
     */

    public void retrieveUserImageFromFirebase(String userName, ImageView userPicture) {
        myDbStorage = FirebaseStorage.getInstance();
        myDbStorageRef = myDbStorage.
                getReferenceFromUrl("gs://virtuallibrary-12090.appspot.com/").
                child("imagesUser/"+ userName + ".png");


        try {
            final File localFile = File.createTempFile("imagesU", "png");
            myDbStorageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    userPicture.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {}
    }

    /**
     * Storing the user profile image in Firebase storage
     * @param bmp
     * @param user
     */

    public void uploadUserImageToFirebase (Bitmap bmp, User user) {
        myDbStorage = FirebaseStorage.getInstance();
        myDbStorageRef = myDbStorage.getReference();

        final StorageReference ImagesRef = myDbStorageRef.child("imagesUser/"+user.getUserName()+".png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        final UploadTask uploadTask = ImagesRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                createToast("Failed to add image");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.i("problem", task.getException().toString());
                        }

                        return ImagesRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri imageURI = task.getResult();
                        }
                        else {
                            createToast("Failed!");
                        }
                    }
                });
            }
        });

    }

    /**
     * Simple method to generate Toast in Android
     * @param toastText
     */

    public void createToast(String toastText) {
        Toast toast = Toast.makeText(this.context, toastText, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
        toast.show();
    }

    public void addBookToOwnedBookList(Book newBook, String current_userName){
        DatabaseReference userRef =  databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> oldBookList = user.getOwnedBookList();

//                boolean isExisted = UserArrayList.stream().anyMatch(user->user.getUserName().equals(username));
//                boolean isExisted = oldBookList.stream().anyMatch()
                boolean listContainsBook = false;
                for(Book book : oldBookList){
                    if (book.getISBN().equals(newBook.getISBN())){
                        listContainsBook = true;
                    }
                }
                if(listContainsBook) {
                    System.out.println("Book is already in owned list");
//                    createToast("This book already exists");
                }else {
                    oldBookList.add(newBook);

                    user.setOwnedBookList(oldBookList);
                    addUser(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayAvailableBooks(String current_userName ,BookRecyclerViewAdapter adapter, ArrayList<Book> availableBookList){
        DatabaseReference bookRef = databaseReference.child("Books");
        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Current user's username: " + current_userName);
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    Book book = data.getValue(Book.class);


                    if((book.getStatus() == BookStatus.AVAILABLE)
                            && !(book.getOwner().equals(current_userName))){
                        System.out.println(book.getOwner() + " == " + current_userName);
                        availableBookList.add(book);
                    }

                }
                System.out.println("Size of the Book list is: " + availableBookList.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendRequest(Book requestedBook, String currentUser){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newRequestedBookList = user.getRequestedBookList();
                System.out.println("ISBN of requested book is " + requestedBook.getISBN());
                newRequestedBookList.add(requestedBook);
                user.setRequestedBookList(newRequestedBookList);
                databaseReference.child("Users").child(currentUser).setValue(user);

                databaseReference.child("Requests").child(requestedBook.getISBN()).child(user.getUserName()).setValue(user);

                userRef.child(requestedBook.getOwner()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        ArrayList<Book> newOwnedBookList = user.getOwnedBookList();
                        for (Book book : newOwnedBookList){
                            if(book.getISBN().equals(requestedBook.getISBN())){
                                newOwnedBookList.remove(book);
                                newOwnedBookList.add(requestedBook);
                                user.setOwnedBookList(newOwnedBookList);
                                userRef.child(requestedBook.getOwner()).setValue(user);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getBookRequests(String isbn, String bookTitle, String current_userName, ArrayList<Request> RequestList, ArrayAdapter adapter){
        DatabaseReference requestRef = databaseReference.child("Requests").child(isbn);
        requestRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);
                    Request request = new Request(user, bookTitle, isbn);
                    System.out.println("Username of user: " + user.getUserName());
                    RequestList.add(request);
                }
                System.out.println("Size of RequestList " + RequestList.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteRequest(Book requestedBook, String requesterUsername){
        DatabaseReference requestRef = databaseReference.child("Requests").child(requestedBook.getISBN()).child(requesterUsername);
        requestRef.removeValue();
        DatabaseReference userRef = databaseReference.child("Users").child(requesterUsername);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newRequestedBookList = user.getRequestedBookList();
                newRequestedBookList.remove(requestedBook);
                user.setRequestedBookList(newRequestedBookList);
                userRef.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void acceptRequest(Book book, String requesterUsername, String bookOwner_userName){
        DatabaseReference userRef = databaseReference.child("Users").child(requesterUsername);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //remove accepted book from all other requester's requested book list
                deleteBookFromAllOtherRequestersRequestedBookList(book, requesterUsername);

                //remove book from requester's requested book list and
                //add it to requester's accepted book list
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newRequesterRequestedBookList = user.getRequestedBookList();
                ArrayList<Book> newRequesterAcceptedBookList = user.getAcceptedBookList();

                //deletes book from accepted user's requested book list
                for(Book Listbook: newRequesterRequestedBookList){
                    if(Listbook.getISBN().equals(book.getISBN())){
                        newRequesterRequestedBookList.remove(Listbook);
                    }
                }
                newRequesterAcceptedBookList.add(book);
                user.setRequestedBookList(newRequesterRequestedBookList);
                user.setAcceptedBookList(newRequesterAcceptedBookList);
                databaseReference.child("Users").child(requesterUsername).setValue(user);
                databaseReference.child("Books").child(book.getISBN()).setValue(book);
                databaseReference.child("Users").child(bookOwner_userName).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User owner = dataSnapshot.getValue(User.class);
                        ArrayList<Book> newOwnedBookList = owner.getOwnedBookList();
                        for (Book bookFromOwnedList: newOwnedBookList){
                            if(bookFromOwnedList.getISBN().equals(book.getISBN())){
                                newOwnedBookList.remove(bookFromOwnedList);
                                newOwnedBookList.add(book);
                                owner.setOwnedBookList(newOwnedBookList);
                                databaseReference.child("Users").child(bookOwner_userName).setValue(owner);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //remove accepted book from all other requester's requested book list
                //deleteBookFromAllOtherRequestersRequestedBookList(book);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteBookFromAllOtherRequestersRequestedBookList(Book book, String acceptedUsername){
        DatabaseReference reqRef = databaseReference.child("Requests").child(book.getISBN());
        reqRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Entered deletefromallother");
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    User user = data.getValue(User.class);
                    System.out.println("Name of user from datasnapshot = " + user.getUserName());
                    if(user.getUserName().equals(acceptedUsername)){
                        System.out.println("Skipping user: "+ user.getUserName());
                        continue;
                    }
                    ArrayList<Book> newRequestedBookList = user.getRequestedBookList();
                    System.out.println("Size of requestedBookList = " + newRequestedBookList.size());
                    for(Book bookFromList : newRequestedBookList){
                        String ISBNFromList = bookFromList.getISBN();
                        String ISBNofDeletedBook = book.getISBN();
                        System.out.println("Inside For Loop");
                        System.out.println("ISBN from list = "+ ISBNFromList);
                        System.out.println("ISBN's of to be deleted book = " + ISBNofDeletedBook);
                        if(ISBNFromList.equals(ISBNofDeletedBook)){
                            System.out.println("ISBN's of book are equal");
                            newRequestedBookList.remove(bookFromList);
                            user.setRequestedBookList(newRequestedBookList);
                            break;
                        }
                    }
                    databaseReference.child("Users").child(user.getUserName()).setValue(user);

                }
                databaseReference.child("Requests").child(book.getISBN()).removeValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void receiveAcceptedBooks(Book book, String current_user){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newAcceptedBookList = user.getAcceptedBookList();
                ArrayList<Book> newBorrowedBookList = user.getBorrowedBookList();

                newBorrowedBookList.add(book);
                for(Book Listbook: newAcceptedBookList){
                    if(Listbook.getISBN().equals(book.getISBN())){
                        newAcceptedBookList.remove(Listbook);
                        break;
                    }
                }
                user.setAcceptedBookList(newAcceptedBookList);
                user.setBorrowedBookList(newBorrowedBookList);
                userRef.child(current_user).setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userRef.child(book.getOwner()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newOwnedBookList = user.getOwnedBookList();

                for(Book Listbook: newOwnedBookList){
                    if(Listbook.getISBN().equals(book.getISBN())){
                        newOwnedBookList.remove(Listbook);
                        newOwnedBookList.add(book);
                        break;
                    }
                }
                user.setOwnedBookList(newOwnedBookList);
                userRef.child(book.getOwner()).setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Books").child(book.getISBN()).setValue(book);
    }

    public void returnBorrowedBook(Book returnedBook, String current_userName){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newBorrowedBookList = user.getBorrowedBookList();
                for (Book book : newBorrowedBookList){
                    if(book.getISBN().equals(returnedBook.getISBN())){
                        newBorrowedBookList.remove(book);
                        user.setBorrowedBookList(newBorrowedBookList);
                        userRef.child(current_userName).setValue(user);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userRef.child(returnedBook.getOwner()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newOwnedBookList = user.getOwnedBookList();
                for(Book book: newOwnedBookList){
                    if(book.getISBN().equals(returnedBook.getISBN())){
                        newOwnedBookList.remove(book);
                        newOwnedBookList.add(returnedBook);
                        user.setOwnedBookList(newOwnedBookList);
                        userRef.child(book.getOwner()).setValue(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("Books").child(returnedBook.getISBN()).setValue(returnedBook);
    }

    /**
    * load all borrowed books by given user from firebase
     * @param returnedBook
     * @see BorrowedBookFragment*/
    public void confirmReturnedBook (Book returnedBook){
        DatabaseReference userRef = databaseReference.child("Users").child(returnedBook.getOwner());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                ArrayList<Book> newOwnedBookList = user.getOwnedBookList();
                for (Book book : newOwnedBookList){
                    if(book.getISBN().equals(returnedBook.getISBN())){
                        newOwnedBookList.remove(book);
                        newOwnedBookList.add(returnedBook);
                        user.setOwnedBookList(newOwnedBookList);
                        userRef.setValue(user);
                        break;
                    }
                }
                databaseReference.child("Books").child(returnedBook.getISBN()).setValue(returnedBook);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayBorrowedBooks(String current_userName, BookRecyclerViewAdapter adapter, ArrayList<Book> borrowedBookList){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                ArrayList<Book> bookList = user.getBorrowedBookList();
                System.out.println("Size of the Book list is: " + bookList.size());
                for(Book book : bookList){
                    borrowedBookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayRequestedBooks(String current_userName, BookRecyclerViewAdapter adapter, ArrayList<Book> requestedBookList){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                ArrayList<Book> bookList = user.getRequestedBookList();
                System.out.println("Size of the Book list is: " + bookList.size());
                for(Book book : bookList){
                    requestedBookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void displayAcceptedBooks(String current_userName, BookRecyclerViewAdapter adapter, ArrayList<Book> acceptedBookList){
        DatabaseReference userRef = databaseReference.child("Users");
        userRef.child(current_userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                ArrayList<Book> bookList = user.getAcceptedBookList();
                System.out.println("Size of the Book list is: " + bookList.size());
                for(Book book : bookList){
                    acceptedBookList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateBookStatusesToBorrowed(Book bookToBeUpdated, String current_userName){
        DatabaseReference userRef = databaseReference.child("Users").child(current_userName);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                System.out.println("Current user's username : " + user.getUserName());
                ArrayList<Book> ownedBookList = user.getOwnedBookList();
                for(Book book: ownedBookList){
                    if (book.getISBN().equals(bookToBeUpdated.getISBN())){
                        ArrayList<Book> updatedOwnedBookList = ownedBookList;
                        updatedOwnedBookList.remove(book);
                        book.setStatus(BookStatus.BORROWED);
                        updatedOwnedBookList.add(book);
                        user.setOwnedBookList(updatedOwnedBookList);
                        addUser(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        DatabaseReference bookRef = databaseReference.child("Books").child(bookToBeUpdated.getISBN());
        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book = dataSnapshot.getValue(Book.class);
                book.setStatus(BookStatus.BORROWED);
                bookRef.setValue(book);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * get book from firebase by provide search terms and add to provide booklist if it existed
     * @param current_userName
     * @param adapter
     * @param availableBookList
     * @param searchTerms
     * @see SearchFragment
     * */
    public void getBooksWithTerms(String current_userName ,BookRecyclerViewAdapter adapter, ArrayList<Book> availableBookList,
                                  String searchTerms){
        DatabaseReference bookRef = databaseReference.child("Books");
        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("Current user's username: " + current_userName);
                availableBookList.clear();
                adapter.notifyDataSetChanged();
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    Book book = data.getValue(Book.class);

                    if((book.getStatus() == BookStatus.AVAILABLE)
                            && !(book.getOwner().equals(current_userName))){
                        System.out.println(book.getOwner() + " == " + current_userName);
                        for(String word: searchTerms.split("\\s+")){
                            if (book.getDescription().toLowerCase().contains(word.toLowerCase())){
                                availableBookList.add(book);
                            }else if(book.getTitle().toLowerCase().contains(word.toLowerCase())){
                                availableBookList.add(book);
                            }else if(book.getAuthor().toLowerCase().contains(word.toLowerCase())){
                                availableBookList.add(book);
                            }else if(book.getISBN().contains(word)){
                                availableBookList.add(book);
                            }

                        }

                    }

                }
                System.out.println("Size of the Book list is: " + availableBookList.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Load book from firebase by its ISBN, add it into BookCallBack to access it when method is called
     * in another activity/fragment
     * @param ISBN
     * @param bookCallBack */
    public void loadBookByISBN(String ISBN, BookCallBack bookCallBack){


        databaseReference.keepSynced(true);
        databaseReference.child(BOOK_PARENT).child(ISBN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book resultBook = dataSnapshot.getValue(Book.class);
                if(resultBook!=null){
                    bookCallBack.onCallback(resultBook);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * load User from firebase by given email, add it into UserCallBack which can be
     * accessed when method is calling in another activity/fragment
     * @param logEmail
     * @param userCallBack */
    public void loadUserInfo(String logEmail, UserCallBack userCallBack) {

        DatabaseReference userRef = databaseReference.child("Users");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    if (logEmail.equals(user.getEmail())) {
                        userCallBack.onCallBack(user);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void SearchUserName(String searchedUsername, UserRecyclerViewAdapter adapter, ArrayList<User> searchedUserList){
        adapter.notifyDataSetChanged();
        DatabaseReference userRef = databaseReference.child("Users").child(searchedUsername);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                searchedUserList.add(user);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    
}