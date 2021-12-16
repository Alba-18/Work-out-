package com.example.work_out_.activities;

import static androidx.test.espresso.Espresso.onView;
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
import org.junit.Test;

public class ChangeProfileTestUI {
    @Rule
    public ActivityTestRule<ProfileActivity> profileRule = new ActivityTestRule<>(ProfileActivity.class);

    @Test
    public void changeAgeProfile(){
        onView(withId(R.id.profileAge)).perform(typeText("23"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.profileSaveButton)).perform(click());
        onView(withId(R.id.profileAge)).check(matches(withText("23")));
    }
}
