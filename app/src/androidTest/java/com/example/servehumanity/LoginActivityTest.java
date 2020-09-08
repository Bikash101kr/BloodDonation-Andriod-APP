package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.LoginActivity;

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
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule =
            new ActivityTestRule<>(LoginActivity.class);
    private  String username = "bikash1";
    private  String password = "12345";
    @Test
    public  void TestUI() throws  Exception{
        onView(withId(R.id.edtUsername)).perform(typeText(username));
        onView(withId(R.id.edtPassword)).perform(typeText(password));

        closeSoftKeyboard();

        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.viewPager));

    }


}
