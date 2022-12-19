package com.cleansample.android_test_shared

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class TextColorMatcher constructor(private val integerMatcher: Matcher<Int>) :
    BoundedMatcher<View?, TextView>(TextView::class.java) {
    public override fun matchesSafely(textView: TextView): Boolean {
        return integerMatcher.matches(textView.currentTextColor)
    }

    override fun describeMismatch(item: Any, mismatchDescription: Description) {
        mismatchDescription.appendText("TextView with text color: " +
                (item as TextView).currentTextColor + ", expected: ")
        integerMatcher.describeMismatch(item, mismatchDescription)
    }

    override fun describeTo(description: Description) {
        description.appendText("with text color ")
        integerMatcher.describeTo(description)
    }

    companion object {
        fun withTextColor(@ColorRes textColor: Int, context: Context): TextColorMatcher {
            val color = context.getColor(textColor)
            return TextColorMatcher(Matchers.`is`(color))
        }
    }
}