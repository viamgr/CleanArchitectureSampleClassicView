package com.cleansample.feature.sample.presentation.detail

sealed class SampleDetailSideEffect {
    data class OpenDocument(val url: String) : SampleDetailSideEffect()
    data class OpenShareIntent(val data: String) : SampleDetailSideEffect()
    data class OpenMapsDirection(val latitude: String, val longitude: String) :
        SampleDetailSideEffect()

    data class OpenPictureScreen(val url: String) : SampleDetailSideEffect()
}