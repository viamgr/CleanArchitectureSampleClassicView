package com.cleansample.ui_common.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter


@BindingAdapter("visibility")
fun View.setVisibility(value: Boolean) {
    isVisible = value
}
