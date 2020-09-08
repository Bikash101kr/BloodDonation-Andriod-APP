package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.EmergencyActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EmergencyActivityTest {
    @Rule
    public ActivityTestRule<EmergencyActivity> testRule =
            new ActivityTestRule<>(EmergencyActivity.class);

    @Test
    public  void TestUI() throws  Exception{


        onView(withId(R.id.webView)).check(matches(isDisplayed()));

    }
}
