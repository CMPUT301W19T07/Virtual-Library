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

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class LogInTest extends ActivityTestRule<LogIn>{


    private Solo solo;

    public LogInTest(){
        super(LogIn.class);
    }

    @Rule
    public ActivityTestRule<LogIn> rule =
            new ActivityTestRule<>(LogIn.class, true, true);

    @Before
    public void SetUp() throws Exception{

        solo = new Solo(getInstrumentation(), rule.getActivity());

    }

    @Test
    public void Traverse() {

        solo.assertCurrentActivity("Wrong Activity", LogIn.class);

        solo.clickOnButton("Sign Up Now");

        solo.assertCurrentActivity("Wrong Activity", SignUp.class);

        solo.clickOnButton("Go Back");

        solo.assertCurrentActivity("Wrong Activity", LogIn.class);
    }

    @Test
    public void Username(){

        solo.assertCurrentActivity("Wrong Activity", LogIn.class);

        solo.enterText((EditText) solo.getView(R.id.USERNAME), "Craig Miller");

        solo.clickOnButton("Log In");

        solo.assertCurrentActivity("Wrong Activity", MainPage.class);

    }

    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }




}
