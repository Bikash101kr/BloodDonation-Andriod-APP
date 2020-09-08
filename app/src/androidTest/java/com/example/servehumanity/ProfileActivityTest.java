package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.ProfileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileActivityTest {
    @Rule
    public ActivityTestRule<ProfileActivity> testRule =
            new ActivityTestRule<>(ProfileActivity.class);
    private  String firstName = "bikash1";
    private  String lastName = "dhakal";
    private  String phone = "9860196032";
    private  String address = "dhading";
    private  String dateOfBirth = "14/12/1998";


    @Test
    public  void TestUI() throws  Exception{
        onView(withId(R.id.edtFirstName)).perform(typeText(firstName));
        onView(withId(R.id.edtLastName)).perform(typeText(lastName));
        onView(withId(R.id.edtPhone)).perform(typeText(phone));
        onView(withId(R.id.edtAddress)).perform(typeText(address));
        onView(withId(R.id.edtAddress)).perform(typeText(address));
        onView(withId(R.id.edtDOB)).perform(typeText(dateOfBirth));


        closeSoftKeyboard();

        onView(withId(R.id.btnCreate)).perform(click());

        onView(withId(R.id.edtUsername));

    }


}
