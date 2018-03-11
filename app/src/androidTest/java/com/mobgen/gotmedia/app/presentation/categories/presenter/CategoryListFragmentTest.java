package com.mobgen.gotmedia.app.presentation.categories.presenter;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.presentation.GotMediaActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created on 3/9/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CategoryListFragmentTest {

    @Rule
    public ActivityTestRule<GotMediaActivity> gotActivityTestRule = new ActivityTestRule<GotMediaActivity>(
            GotMediaActivity.class, true, false);


    public void startActivity(){
        gotActivityTestRule.launchActivity(null);
    }

    @Test
    public void openGotListFragmentAndCheckTitleList(){
        startActivity();

//        onView(withId(R.id.toolBarTitle)).check(matches(withText(startsWith(MY_CAR_ATTRIBUTES_TITLE))));
    }

}