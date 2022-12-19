package com.cleansample.core

import com.cleansample.core.errors.BaseError

class WrappedResult<T> private constructor(internal val result: Result<T>) {
    fun <T> WrappedResult<T>.`get`(): Result<T> {
        return result
    }

    public val isFailure: Boolean get() = result.isFailure
    public val isSuccess: Boolean get() = result.isSuccess

    companion object {

        public fun <T> success(value: T): WrappedResult<T> =
            WrappedResult(Result.success(value))

        public fun <T> failure(exception: Throwable): WrappedResult<T> =
            WrappedResult(Result.failure(exception))

        public fun <R, T> WrappedResult<T>.map(transform: (value: T) -> R): WrappedResult<R> {
            return when {
                isSuccess -> WrappedResult.success(transform(result.getOrThrow()))
                isFailure -> WrappedResult.failure(result.exceptionOrNull()!!)
                else -> WrappedResult<R>(result as Result<R>)
            }
        }
    }
}

fun <R, T : BaseError> Throwable.toResult(baseError: T): WrappedResult<R> {
    return WrappedResult.failure(BaseException(baseError, this))
}


public fun <T> WrappedResult<T>.getOrThrow(): T {
    return get().getOrThrow()
}

internal fun WrappedResult<*>.throwOnFailure() {
    get().getOrThrow()
}


public inline fun <R, T> WrappedResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R,
): R = get().fold(onSuccess, onFailure)

public inline fun <T> WrappedResult<T>.onSuccess(action: (value: T) -> Unit): Result<T> =
    get().onSuccess(action)


fun <R> R.toSuccess(): WrappedResult<R> {
    return WrappedResult.success(this)
}