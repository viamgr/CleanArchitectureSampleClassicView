package com.cleansample.feature.sample.presentation.pattern

import com.cleansample.core.WrappedResult
import com.cleansample.core.fold
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.OrbitDsl
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.reduce

@OrbitDsl
internal suspend fun <STATE : State, SIDE_EFFECT : Any> SimpleSyntax<STATE, SIDE_EFFECT>.reduceResult(
    resultBlock: suspend () -> WrappedResult<STATE>,
    loadingDebounce: Long = 250,
    onLoading: ((STATE, Boolean) -> STATE)? = null,
    onError: ((STATE, error: Throwable) -> STATE)? = null,
    onSuccess: ((STATE) -> STATE)? = null,
) = coroutineScope {
    val loadingJob = launch {
        delay(loadingDebounce)
        onLoading?.let {
            reduce { it.invoke(state, true) }
        }
    }
    launch {
        val result = resultBlock()
        loadingJob.cancel()
        reduce {
            result.fold(onSuccess = {
                val newState = onLoading?.invoke(it, false) ?: it
                onSuccess?.invoke(newState) ?: newState
            }, onFailure = {
                val newState = onLoading?.invoke(state, false) ?: state
                onError?.invoke(newState, it) ?: newState
            })
        }
    }
}
