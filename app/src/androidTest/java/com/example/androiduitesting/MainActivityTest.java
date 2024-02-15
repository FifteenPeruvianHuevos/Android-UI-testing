package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Class that will test the UI capabilities/code implementations for the MainActivity
 * */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    // IOT test MainActivity we need to make a "Rule"
    /*
    This rule provides functional testing of a single activity. The rule launches the
    chosen activity before each test annotated with @Test.
    ActivityScenario provides APIs to start and drive an Activity's lifecycle state for
    testing.
    * */

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    // Now we are set to write our first Test
    // We will click on the Add City Button and enter the name of the city
    // and then we will check if that city is displayed. If the city is displayed
    // then the test should be pass else it should fail
    @Test
    public void testAddCity(){
        // Click on Add City Button
        onView(withId(R.id.button_add)).perform(click());

        // Type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // Click on Confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // Check if text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    // Now we will write our second test -
    // We will add cities and then click on Clear All button, if the cities
    // displayed are removed then test should pass else it should fail
    @Test
    public void testClearCity(){
        // Add first City to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Add another City to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Clear the list and check if it worked
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    // Test number three -
    // We see if the city “Edmonton” that we add is located at position 0 of
    // the list
    @Test
    public void testListView(){
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Check if in the Adapter view (given id of that adapter view),
        // there is a data
        // (which is an instance of String) located at position zero.
        // If this data matches the text we provided then Voila! Our test
        // should pass
        // You can also use anything() in place of
        // is(instanceOf(String.class))
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list
        )).atPosition(0).check(matches((withText("Edmonton"))));
    }

    // Test for Correctly Switching Activity
    @Test
    public void testActivitySwap(){

    }

    // Test for City Name Consistency
    @Test
    public void testCityConsistenvy(){

    }

    // Test for Back Button functionality (Just the other direction of ActivitySwap
    @Test
    public void testBackButton(){

    }
}
