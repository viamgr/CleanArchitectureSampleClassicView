package com.cleansample.ui_common.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.cleansample.ui_common.R

/**
 * Helper to load images using Coil.
 */
@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(image: String) {
    load(image) {
        crossfade(true)
        error(R.drawable.ic_baseline_warning_24)
        placeholder(R.drawable.ic_baseline_more_horiz_24)
    }
}