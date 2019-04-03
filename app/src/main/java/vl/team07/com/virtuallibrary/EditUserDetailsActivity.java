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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUserDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Button acceptChanges;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_details);


        EditText nameText = findViewById(R.id.nameText);
        EditText Age = findViewById(R.id.ageText);
        EditText Nationality = findViewById(R.id.nationalityText);
        imageView = findViewById(R.id.imageView2);
        acceptChanges = findViewById(R.id.acceptChangesBtn);


//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = firebaseAuth.getCurrentUser();
//        String logEmail = firebaseUser.getEmail();
        DatabaseHandler dh = DatabaseHandler.getInstance(getApplicationContext());

//        dh.loadUserInfo(logEmail, new UserCallBack() {
//            @Override
//            public void onCallBack(User user) {
//                dh.retrieveUserImageFromFirebase(user.getUserName(), imageView);
//            }
//        });
        Bitmap bmp_old = ((BitmapDrawable) imageView.getDrawable()).getBitmap();


        acceptChanges.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                String logEmail = firebaseUser.getEmail();
                DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());

                databaseHandler.loadUserInfo(logEmail, new UserCallBack() {
                    @Override
                    public void onCallBack(User user) {
                        Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                        if(bmp_old != bmp){
                            dh.uploadUserImageToFirebase(bmp, user);
                        }

                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                        if(!nameText.getText().toString().isEmpty()){
                            dbRef.child("Users").child(user.getUserName()).child("name").setValue(nameText.getText().toString());
                        }
                        if(!Nationality.getText().toString().isEmpty()){
                            dbRef.child("Users").child(user.getUserName()).child("nationality").setValue(Nationality.getText().toString());
                        }
                        if(!Age.getText().toString().isEmpty()){
                            dbRef.child("Users").child(user.getUserName()).child("age").setValue(Integer.parseInt(Age.getText().toString()));
                        }

                        Toast toast = Toast.makeText(EditUserDetailsActivity.this, "Changes made successfully!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                        toast.show();

                    }
                });

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


    /**
     * Starting the activity of the gallery, to pick the User profile picture
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
}
