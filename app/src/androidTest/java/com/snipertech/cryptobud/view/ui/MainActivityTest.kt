package com.snipertech.cryptobud.view.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.snipertech.cryptobud.R

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivity_isViewedCorrectly() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun welcomeText_isViewedCorrectly() {
        onView(withId(R.id.welcomeView)).check(matches(withText(
            R.string.welcome
        )))
    }

    @Test
    fun encryptedMessage_isViewedCorrectly() {
        onView(withId(R.id.encryptedMessageView)).check(matches(withText(
            R.string.encrypted
        )))
    }

    @Test
    fun floatingButton_isViewedCorrectly() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun homeFragment_isViewedCorrectly() {
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))
    }
}
