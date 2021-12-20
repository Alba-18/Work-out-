package com.example.work_out_.Espresso_Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import com.example.work_out_.ProfileActivity;
import com.example.work_out_.R;
import org.junit.Rule;
import static org.hamcrest.core.StringContains.containsString;
import org.junit.Test;

public class ChangeProfileTestUI {
    @Rule
    public ActivityTestRule<ProfileActivity> profileRule = new ActivityTestRule<>(ProfileActivity.class);

    //This espresso test goes to the profile activity and checks if the age can be changed
    @Test
    public void changeAgeProfile(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.profileAge)).perform(clearText());
        onView(withId(R.id.profileAge)).perform(typeText("23"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.profileAge)).check(matches(withText(containsString("23"))));
    }
}
