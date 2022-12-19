package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.airbnb.epoxy.group
import com.cleansample.feature.sample.ui.R
import com.cleansample.feature.sample.ui.feature
fun ModelCollector.featureList(features: List<String>) {

    for (index in features.indices step 2) {

        group {
            id("group", "featureList")
            layout(R.layout.wrapper_feature_list) // You must provide your own layout file here

            feature {
                id("feature", features[index])
                label(features[index])
            }

            features.getOrNull(index + 1)?.let {
                feature {
                    id("feature", it)
                    label(it)
                }
            }

        }
        divider()
    }


}