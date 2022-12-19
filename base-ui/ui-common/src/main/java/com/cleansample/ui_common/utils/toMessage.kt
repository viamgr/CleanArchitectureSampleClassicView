package com.cleansample.ui_common.utils

import android.content.res.Resources
import com.cleansample.core.errors.*
import com.cleansample.ui_common.R

fun BaseError.toMessage(resources: Resources) = when (this) {
    is ItemNotFound<*> -> {
        resources.getString(R.string.error_format_2,
            javaClass.name,
            item.toString())
    }
    NoInternet, Timeout, UnknownHost, ForbiddenError, UnAuthorized, UnknownError -> resources.getString(
        R.string.error_format,
        javaClass.name)
    is ApiError -> resources.getString(
        R.string.error_format_3,
        javaClass.name,
        code.toString(),
        message
    )
    is InvalidItemId<*> -> resources.getString(
        R.string.error_format_2,
        javaClass.name,
        id,
    )
}
