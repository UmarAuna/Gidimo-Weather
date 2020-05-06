package com.poem.gidimoweather

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poem.gidimoweather.ui.NewLocation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class NewLocationTest {
    @Rule
    var intentsTestRule: IntentsTestRule<NewLocation> = IntentsTestRule(NewLocation::class.java)

    @Test
    fun NewLocation() {
        //onView(withId(R.id.admin_signup_create)).perform(click());

        val location= "Kano"
        Espresso.onView(withId(R.id.editText_current_location)).perform(ViewActions.typeText(location))
        Espresso.onView(withId(R.id.btn_save_location)).perform(ViewActions.click())
    }
}