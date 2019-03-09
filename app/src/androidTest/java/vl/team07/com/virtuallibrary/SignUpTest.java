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

import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.*;

public class SignUpTest extends ActivityTestRule<SignUp>{


    private Solo solo;

    public SignUpTest(){
        super(SignUp.class);
    }

    @Rule
    public ActivityTestRule<SignUp> rule =
            new ActivityTestRule<>(SignUp.class, true, true);

    @Before
    public void SetUp() throws Exception{

        solo = new Solo(getInstrumentation(), rule.getActivity());

    }

    @Test
    public void DataCheck(){

        SignUp activity = (SignUp) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", SignUp.class);

        solo.enterText((EditText) solo.getView(R.id.Uname), "Craig Miller");
        solo.enterText((EditText) solo.getView(R.id.Name), "Name");
        solo.enterText((EditText) solo.getView(R.id.Email), "cjmiller@ualberta.ca");

        assertTrue(activity.uniqueUsername("Test"));
        assertTrue(activity.uniqueUsername("Craig J Miller"));
        assertTrue(activity.checkEmail("test123@gmail.com"));
        assertFalse(activity.checkEmail("test123gmail.com"));
        assertFalse(activity.checkEmail("test123@gmailcom"));

        solo.clickOnButton("Sign Up");

        solo.assertCurrentActivity("Wrong Activity", LogIn.class);
    }

    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }




}
