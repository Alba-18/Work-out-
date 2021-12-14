package com.example.work_out_.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.MainActivity;
import com.example.work_out_.R;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
public class PushupsStartActivityTest {

    @Rule
    public ActivityTestRule<PushupsStartActivity> activityRule = new ActivityTestRule<>(PushupsStartActivity.class);

    @Test
    public void openPopup(){
        onView(withId(R.id.HelpPushUp)).perform(click());
        onView(withText("close")).check(matches(isDisplayed()));
    }
}