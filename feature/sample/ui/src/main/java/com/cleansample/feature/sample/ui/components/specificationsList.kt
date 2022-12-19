package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.presentation.model.Attribute
import com.cleansample.feature.sample.ui.specification

fun ModelCollector.specificationList(specifications: List<Attribute>) {
    for (specification in specifications) {
        specification {
            id("specification", specification.label)
            label(specification.label)
            value(specification.value)
            unit(specification.unit.orEmpty())
        }
        divider()
    }
}