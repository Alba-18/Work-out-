package com.example.work_out_.Espresso_Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.Activities.MapActivity;
import com.example.work_out_.Activities.PushupsStartActivity;
import com.example.work_out_.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MapTestUI {

    @Rule
    public ActivityTestRule<MapActivity> mapRule = new ActivityTestRule<>(MapActivity.class);

    //This espresso test checks if the map is displayed and the text is correct
    @Test
    public void openPopup(){
        onView(withId(R.id.google_map)).check(matches(isDisplayed()));
        onView(withId(R.id.Title_Map)).check(matches(withText(containsString("Parks near you:"))));
    }
}