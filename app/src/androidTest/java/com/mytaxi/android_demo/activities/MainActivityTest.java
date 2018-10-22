package com.mytaxi.android_demo.activities;

import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import com.mytaxi.android_demo.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    private String uUsername = "crazydog335";
    private String pPassword = "venture";
    private String sSearchString = "sa";


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();

        //login into myTaxi Demo
        waitNseconds(5);
        onView(withId(R.id.edt_username))
                .perform(click());

        onView(withId(R.id.edt_username))
                .perform(replaceText(uUsername), closeSoftKeyboard());

        onView(withId(R.id.edt_password))
                .perform(replaceText(pPassword), closeSoftKeyboard());

        onView(withId(R.id.btn_login))
                .perform(click());

}

    @Test
    public void testCallSearchResult()
    {
        waitNseconds(5);

        //Search for "sa" text in the input text bar
        onView(withId(R.id.textSearch))
            .perform(typeText(sSearchString));
        waitNseconds(5);

        //Select 2nd result by Text = Sarah Scott from the list
        onView(withText("Sarah Scott"))
                .inRoot(RootMatchers.withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        waitNseconds(5);

        //Click Call button
        onView(withId(R.id.fab))
                .inRoot(RootMatchers.withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
    }
    @After
    public void tearDown() throws Exception {
    }

    private static void waitNseconds(int N){
        try {
            Thread.sleep(N*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}