package com.example.work_out_.Espresso_Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.Activities.PushupsStartActivity;
import com.example.work_out_.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StartPushupsTestUI {

    @Rule
    public ActivityTestRule<PushupsStartActivity> pushupsStartRule = new ActivityTestRule<>(PushupsStartActivity.class);

    @Test
    public void openPopup(){
        onView(withId(R.id.HelpPushUp)).perform(click());
        onView(withId(R.id.textActivitiesPopup)).check(matches(withText("On this screen you will see the detailed information for each activity")));
    }
}