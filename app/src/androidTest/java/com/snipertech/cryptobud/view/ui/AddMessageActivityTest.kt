package com.snipertech.cryptobud.view.ui
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.snipertech.cryptobud.R
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddMessageActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AddMessageActivity::class.java)

    @Test
    fun addMessageActivity_isViewedCorrectly() {
        onView(withId(R.id.addItemActivity))
            .check(matches(isDisplayed()))
    }


    @Test
    fun encryptButton_isDisplayedCorrectly() {
        onView(withId(R.id.encrypt))
            .check(matches(isDisplayed()))

        onView(withId(R.id.encrypt))
            .check(matches(withText(R.string.button_encrypt)))
    }


    @Test
    fun decryptButton_isDisplayedCorrectly() {
        onView(withId(R.id.decrypt))
            .check(matches(isDisplayed()))

        onView(withId(R.id.decrypt))
            .check(matches(withText(R.string.button_decrypt)))
    }

    @Test
    fun encryptionAlgorithmSpinner_isDisplayedCorrectly() {
        onView(withId(R.id.spinner_encryption_type))
            .check(matches(isDisplayed()))
    }

}