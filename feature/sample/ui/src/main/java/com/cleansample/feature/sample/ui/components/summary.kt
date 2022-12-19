package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.presentation.model.Summary
import com.cleansample.feature.sample.ui.summary

fun ModelCollector.summary(summary: Summary, onAddressClickedListener: () -> Unit) {
    summary {
        id("summary")
        sampleId(summary.id)
        title(summary.title)
        currency(summary.currency)
        price(summary.price)
        visits(summary.visits)
        date(summary.date)
        street(summary.street)
        zipCode(summary.zipCode)
        city(summary.city)
        onAddressClickedListener(onAddressClickedListener)
    }
}
