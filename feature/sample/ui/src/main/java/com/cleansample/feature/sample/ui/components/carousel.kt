package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelCollector
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import com.cleansample.feature.sample.ui.R
import com.cleansample.feature.sample.ui.photo
import com.cleansample.feature.sample.ui.share
import com.cleansample.ui_common.utils.slides

fun ModelCollector.carousel(
    pictures: List<String>,
    shareButtonVisibility: Boolean,
    onCarouselItemClickedListener: (Int) -> Unit,
    onShareClickedListener: () -> Unit,
) {
    group {
        id("carouselWrapper")
        layout(R.layout.wrapper_carousel) // You must provide your own layout file here

        carousel {
            id("carousel")
            numViewsToShowOnScreen(1F)
            padding(Carousel.Padding(0, 0))
            slides {
                for (picture in pictures.withIndex()) {
                    photo {
                        imageUrl(picture.value)
                        id(picture.value)
                        index(picture.index + 1)
                        total(pictures.size)
                        onItemClickListener {
                            onCarouselItemClickedListener(picture.index)
                        }
                    }
                }
            }
        }

        if (shareButtonVisibility) {
            share {
                id("share")
                onShareClickedListener {
                    onShareClickedListener()
                }
            }
        }

    }

}