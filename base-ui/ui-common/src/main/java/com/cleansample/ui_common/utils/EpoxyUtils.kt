package com.cleansample.ui_common.utils

import com.airbnb.epoxy.CarouselModelBuilder
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector

inline fun CarouselModelBuilder.slides(
    crossinline inputModelCollector: ModelCollector.() -> Unit,
) {
    val items = mutableListOf<EpoxyModel<*>>()

    val modelCollectorMiddleMan: ModelCollector.() -> Unit = {
        inputModelCollector()
    }

    modelCollectorMiddleMan(object : ModelCollector {
        override fun add(model: EpoxyModel<*>) {
            items.add(model)
        }
    }
    )
    models(items)
}

