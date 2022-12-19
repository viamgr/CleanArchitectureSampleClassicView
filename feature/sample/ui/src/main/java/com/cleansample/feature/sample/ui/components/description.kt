package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.ui.description

fun ModelCollector.description(description: String) {
    description {
        id("description")
        text(description)
    }
}