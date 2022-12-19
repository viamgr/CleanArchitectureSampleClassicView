package com.cleansample.core.errors

import com.cleansample.core.BaseException

sealed interface BaseError

sealed interface RemoteError : BaseError

sealed interface ServerError : RemoteError

sealed interface NetworkError : RemoteError

object Timeout : NetworkError
object UnknownHost : NetworkError
object NoInternet : NetworkError

object UnknownError : BaseError

object ForbiddenError : ServerError
object UnAuthorized : ServerError
data class ApiError(val code: Int, val message: String) : ServerError

data class InvalidItemId<T>(val id: T) : ServerError
data class ItemNotFound<T>(val item: T) : ServerError

fun BaseError.toBaseException(): BaseException {
    return BaseException(this)
}
