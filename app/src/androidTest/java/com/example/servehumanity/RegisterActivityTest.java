package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> testRule =
            new ActivityTestRule<>(RegisterActivity.class);
    private  String username = "bikash1222";
    private  String password = "12345";
    private  String confirmPassword = "12345";
    private  String email = "dhakalbikash0@gmail.com";
    @Test
    public  void TestUI() throws  Exception{
        onView(withId(R.id.edtUsername)).perform(typeText(username));
        onView(withId(R.id.edtPassword)).perform(typeText(password));
        onView(withId(R.id.edtConfirmPassword)).perform(typeText(confirmPassword));
        onView(withId(R.id.edtEmail)).perform(typeText(email));

        closeSoftKeyboard();

        onView(withId(R.id.btnRegister)).perform(click());

        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()));

    }
}
