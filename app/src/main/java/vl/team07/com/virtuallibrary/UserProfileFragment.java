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
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.Locale;


public class UserProfileFragment extends android.support.v4.app.Fragment {


    private TextView nameText, usernameText, ageText, nationalityText, contactInfoText, addressText;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button signOut;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View UserProfileView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        getActivity().setTitle("User Profile");

        nameText = (TextView) UserProfileView.findViewById(R.id.nameText);
        usernameText = (TextView) UserProfileView.findViewById(R.id.usernameText);
        ageText = (TextView) UserProfileView.findViewById(R.id.ageText);
        nationalityText = (TextView) UserProfileView.findViewById(R.id.nationalityText);
        contactInfoText = (TextView) UserProfileView.findViewById(R.id.contactInfoText);
        addressText = (TextView) UserProfileView.findViewById(R.id.addressText);

        signOut = UserProfileView.findViewById(R.id.signOutBtn);

        setUserInfo();

        /**
         * Uses Firebase Authentication to signout the the current user
         *
         * @author Imtiaz raqib
         */
        signOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Context context = v.getContext();
                Intent intent = new Intent(context, LogIn.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });

        return UserProfileView;

    }


    // Temp use to test
    public void setUserInfo(){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        // User should be replaced by load from firebase
        User user = new User("Test username", "Test name", "0", "Test email", 18, "Canada", 0, "Edmonton");

        nameText.setText(String.format(Locale.CANADA, "Name: %s", user.getName()));
        usernameText.setText(String.format(Locale.CANADA, "Username: %s", user.getUserName()));
        ageText.setText(String.format(Locale.CANADA, "Age: %d", user.getAge()));
        nationalityText.setText(String.format(Locale.CANADA, "Nationality: %s", user.getNationality()));
        contactInfoText.setText(String.format(Locale.CANADA, "Contact Info: %s", firebaseUser.getEmail()));
        addressText.setText(String.format(Locale.CANADA, "Address: %s", user.getAddress()));
    }


}
