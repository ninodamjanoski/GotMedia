package com.mobgen.gotmedia.app.presentation.categories.presenter;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.presentation.GotMediaActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;

/**
 * Created on 3/9/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CategoriesFragmentTest {

    @Rule
    public ActivityTestRule<GotMediaActivity> gotActivityTestRule = new ActivityTestRule<GotMediaActivity>(
            GotMediaActivity.class, true, false);


    public void startActivity(){
        gotActivityTestRule.launchActivity(null);
    }

    @Test
    public void openCateFragmentAndCheckTitleList(){
        startActivity();

        onView(withItemText("Characters")).check(matches(isDisplayed()));
    }

    @Test
    public void openListFragmentAndCheckTitleList(){
        startActivity();

        onView(withItemText("Characters")).perform(click());

        onView(withText("Characters")).check(matches(isDisplayed()));
    }

    private Matcher<View> withItemText(final String itemText) {
        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty");
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View item) {
                return allOf(
                        isDescendantOfA(isAssignableFrom(RecyclerView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is isDescendantOfA LV with text " + itemText);
            }
        };
    }

}