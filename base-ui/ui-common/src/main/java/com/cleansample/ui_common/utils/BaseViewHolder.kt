package com.cleansample.ui_common.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<out T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(type: @UnsafeVariance T)
}