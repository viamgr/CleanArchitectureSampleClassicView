package com.cleansample.feature.sample.ui.components

import android.view.View
import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.ui.dividerLarge

fun ModelCollector.largeDivider() {
    dividerLarge {
        id(View.generateViewId())
    }
}
