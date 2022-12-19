package com.cleansample.android_test_shared

import android.content.Context
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry


fun checkText(@IdRes id: Int, text: String) {
    onView(withId(id)).check(matches(withText(text)))
}

fun checkDoesNotExist(@IdRes id: Int) {
    onView(withId(id)).check(doesNotExist())
}

fun checkExistWithText(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
}

fun getContext(): Context {
    return InstrumentationRegistry.getInstrumentation().targetContext
}