package com.cleansample.feature.sample.ui.components

import androidx.annotation.StringRes
import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.ui.header

fun ModelCollector.header(@StringRes resourceId: Int) {
    header {
        id(resourceId)
        title(resourceId)
    }
    divider()
}