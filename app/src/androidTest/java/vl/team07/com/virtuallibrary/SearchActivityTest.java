/*
 * SearchActivityTest
 *
 * March 11, 2019
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest extends ActivityTestRule<MainActivity> {

    private Solo solo;

    public SearchActivityTest() {
        super(MainActivity.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();

    }

    @Test
    public void clickOnSearch() {
        MainActivity activity = (MainActivity) solo.getCurrentActivity();

        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

        solo.clickOnText("Borrowed Books");
        solo.clickOnImage(11);
        solo.assertCurrentActivity("Wrong Activity", SearchFragment.class);
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
