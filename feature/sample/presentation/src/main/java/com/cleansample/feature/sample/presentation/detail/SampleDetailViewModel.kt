package com.cleansample.feature.sample.presentation.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.cleansample.core.toBaseError
import com.cleansample.feature.sample.presentation.IMAGE_SIZE_KEY
import com.cleansample.feature.sample.presentation.IMAGE_SIZE_LARGE
import com.cleansample.feature.sample.presentation.pattern.reduceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SampleDetailViewModel @Inject constructor(
    private val getSampleDetailResponse: GetSampleDetailResponse,
) : ViewModel(), ContainerHost<SampleDetailState, SampleDetailSideEffect> {

    private val initialState = SampleDetailState()
    override val container: Container<SampleDetailState, SampleDetailSideEffect> =
        container(initialState)

    init {
        loadSampleDetailIntent()
    }

    //  ---> START <--- INTENT WITH SIDE EFFECTS ---> START <---

    @VisibleForTesting
    fun loadSampleDetailIntent() = intent {
        reduceResult(
            loadingDebounce = if (state.error == null) 0L else 250L,
            resultBlock = {
                getSampleDetailResponse(initialState)
            },
            onLoading = { state, isLoading ->
                state.copy(
                    isLoading = isLoading,
                    error = null,
                    shareButtonVisibility = !isLoading
                )
            },
            onError = { state, throwable ->
                state.copy(
                    error = throwable.toBaseError(),
                    shareButtonVisibility = false
                )
            })
    }

    private fun handleOpenDocumentIntent(documentUrl: String) = intent {
        postSideEffect(SampleDetailSideEffect.OpenDocument(documentUrl))
    }

    private fun handleShareClickedListenerIntent() = intent {
        postSideEffect(SampleDetailSideEffect.OpenShareIntent(getSampleDetailResponse.sampleId.toString()))
    }

    private fun handleAddressClickedListener() = intent {
        postSideEffect(SampleDetailSideEffect.OpenMapsDirection(
            latitude = getSampleDetailResponse.cachedLatitude,
            longitude = getSampleDetailResponse.cachedLongitude
        ))
    }

    private fun handleCarouselItemClickedListenerIntent(position: Int) = intent {
        getSampleDetailResponse.cachedPictures.getOrNull(position)?.let {
            postSideEffect(SampleDetailSideEffect.OpenPictureScreen(
                it.replace(IMAGE_SIZE_KEY, IMAGE_SIZE_LARGE)
            ))
        }
    }

    //  ---> END <--- INTENT WITH SIDE EFFECTS ---> END <---


    //  ---> START <---  EVENTS ---> START <---
    fun onDocumentClickListenerEvent(documentUrl: String) {
        handleOpenDocumentIntent(documentUrl)
    }

    fun onRetryClickedListenerEvent() {
        loadSampleDetailIntent()
    }

    fun onCarouselItemClickedListenerEvent(position: Int) {
        handleCarouselItemClickedListenerIntent(position)
    }

    fun onShareClickedListenerEvent() {
        handleShareClickedListenerIntent()
    }

    fun onAddressClickedListenerEvent() {
        handleAddressClickedListener()
    }
    //  ---> START <--- EVENTS ---> START <---

}