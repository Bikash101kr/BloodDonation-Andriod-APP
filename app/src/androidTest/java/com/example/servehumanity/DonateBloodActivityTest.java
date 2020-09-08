package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.DonateBloodActivity;

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
public class DonateBloodActivityTest {
    @Rule
    public ActivityTestRule<DonateBloodActivity> testRule =
            new ActivityTestRule<>(DonateBloodActivity.class);
    private  String weight = "60";
    private  String country = "nepal";
   private  String donationDate = "dec2019";
    private  String location = "Dhakal Clinic";
    @Test
    public  void TestUI() throws  Exception{
        onView(withId(R.id.edtWeight)).perform(typeText(weight));
        onView(withId(R.id.edtCountry)).perform(typeText(country));
        onView(withId(R.id.edtDate)).perform(typeText(donationDate));
        onView(withId(R.id.edtLocation)).perform(typeText(location));

        closeSoftKeyboard();

        onView(withId(R.id.btnConfirmDonation)).perform(click());

        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()));

    }
}
