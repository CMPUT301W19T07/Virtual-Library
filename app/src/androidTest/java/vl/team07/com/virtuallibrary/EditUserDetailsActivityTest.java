package vl.team07.com.virtuallibrary;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EditUserDetailsActivityTest extends ActivityTestRule<EditUserDetailsActivity> {

    private Solo solo;

    public EditUserDetailsActivityTest() {
        super(EditUserDetailsActivity.class);
    }

    @Rule
    public ActivityTestRule<EditUserDetailsActivity> rule =
            new ActivityTestRule<>(EditUserDetailsActivity.class, true, true);


    @Before
    public void SetUp() throws Exception{

        solo = new Solo(getInstrumentation(), rule.getActivity());

    }

    @Test
    public void ClickOnAcceptChangesButton() throws Exception {

        solo.assertCurrentActivity("Wrong Activity", EditUserDetailsActivity.class);

        solo.clickOnButton("Accept Changes");
        assertTrue(solo.waitForText("Changes made successfully!"));
    }

    @Test
    public void EditUserDetails() throws Exception {

        solo.assertCurrentActivity("Wrong Activity", EditUserDetailsActivity.class);

        solo.enterText((EditText) solo.getView(R.id.nameText), "New Test Case Name");
        solo.enterText((EditText) solo.getView(R.id.nationalityText), "TestCase Country");

        solo.clickOnButton("Accept Changes");
        assertTrue(solo.waitForText("Changes made successfully!"));
    }


    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
