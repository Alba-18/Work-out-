package com.example.work_out_.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PushupsStartActivityTest {

    @Rule
    public ActivityTestRule<PushupsStartActivity> activityRule = new ActivityTestRule<>(PushupsStartActivity.class);

    @Test
    public void openPopup(){
        onView(withId(R.id.HelpPushUp)).perform(click());
        onView(withId(R.id.id_close)).check(matches(isDisplayed()));
    }
}