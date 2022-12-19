package com.cleansample.core

import com.cleansample.core.errors.BaseError
import com.cleansample.core.errors.UnknownError

data class BaseException(val baseError: BaseError, override val cause: Throwable? = null) :
    Throwable()

fun Throwable.toBaseError(): BaseError {
    return if (this is BaseException) {
        this.baseError
    } else UnknownError
}