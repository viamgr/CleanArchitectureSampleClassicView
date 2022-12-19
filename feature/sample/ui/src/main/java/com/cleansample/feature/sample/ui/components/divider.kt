package com.cleansample.feature.sample.ui.components

import android.view.View
import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.ui.divider

fun ModelCollector.divider() {
    divider {
        id(View.generateViewId())
    }
}
