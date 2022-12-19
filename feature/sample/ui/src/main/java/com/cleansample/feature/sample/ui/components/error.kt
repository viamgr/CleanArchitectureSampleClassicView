package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.ui.errorUi

internal fun ModelCollector.error(
    onRetryClickedListener: () -> Unit,
    text: String,
) {
    errorUi {
        id("error")
        text(text)
        onRetryClickedListener {
            onRetryClickedListener()
        }

    }
}
