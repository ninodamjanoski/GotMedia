package com.mobgen.gotmedia.app.presentation.categories.presenter;

import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.mobgen.gotmedia.presentation.view.activity.GotMediaActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.Matchers.allOf;

/**
 * Created on 3/12/18.
 */

public class CategoriesFragmentTestBase {


    @Rule
    public ActivityTestRule<GotMediaActivity> gotActivityTestRule = new ActivityTestRule<GotMediaActivity>(
            GotMediaActivity.class, true, false);


    public void startActivity(){
        gotActivityTestRule.launchActivity(null);
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
