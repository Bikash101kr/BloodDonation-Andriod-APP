package com.example.servehumanity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.servehumanity.Activity.DashboardActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FragmentTest {
    @Rule
    public ActivityTestRule<DashboardActivity>activityTestRule =
            new ActivityTestRule<>(DashboardActivity.class);

    @Before
    public void setUpFragment(){
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
            }
            @Test
    public void testFragment(){

                onView(withId(R.id.tabLayout)).perform(click());
            }

}
