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
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditUserDetailsFragment extends Fragment {


    private EditText nameText, usernameText, ageText, nationalityText,contactInfoText, addressText;
    private Button acceptBtn;

    private Context myContext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View editUserDetails = inflater.inflate(R.layout.fragment_edit_user_details, container,false);

        nameText = (EditText) editUserDetails.findViewById(R.id.nameText);
        usernameText = (EditText) editUserDetails.findViewById(R.id.usernameText);
        ageText = (EditText) editUserDetails.findViewById(R.id.ageText);
        nationalityText = (EditText) editUserDetails.findViewById(R.id.ageText);
        contactInfoText = (EditText) editUserDetails.findViewById(R.id.contactInfoText);
        addressText = (EditText) editUserDetails.findViewById(R.id.addressText);

        acceptBtn = (Button)editUserDetails.findViewById(R.id.acceptChangesBtn);





        return editUserDetails;
    }

    @Override
    public void onStart(){
        super.onStart();



    }




}
