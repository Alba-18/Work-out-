package com.example.work_out_.Espresso_Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.LoginActivity;
import com.example.work_out_.R;

import org.junit.Rule;
import org.junit.Test;

public class LoginTestUI {
    @Rule
    public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void doPushups(){
        onView(withId(R.id.dopushup)).perform(click());
        onView(withId(R.id.pushups_counter)).check(matches(withText("1")));
    }
}
