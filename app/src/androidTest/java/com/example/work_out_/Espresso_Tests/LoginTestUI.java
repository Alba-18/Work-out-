package com.example.work_out_.Espresso_Tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.work_out_.LoginActivity;
import com.example.work_out_.R;

import org.junit.Rule;
import org.junit.Test;

public class LoginTestUI {
    @Rule
    public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule<>(LoginActivity.class);

    //This espresso test logs with an existing user and sees if the login is done successfully
    @Test
    public void doLogin(){
        onView(withId(R.id.user_Name_Login)).perform(typeText("miriamdefco@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.user_Password)).perform(typeText("123456"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
    }
}
