package com.cleansample.feature.sample.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.cleansample.core.WrappedResult
import com.cleansample.core.WrappedResult.Companion.map
import com.cleansample.feature.sample.presentation.mapper.mapAdDetail
import com.cleansample.use_case.samples.GetSampleDetailUseCase
import javax.inject.Inject

class GetSampleDetailResponse @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSampleDetailUseCase: GetSampleDetailUseCase,
) {
    val sampleId: Long = savedStateHandle.get<Long>("sampleId") ?: error(IllegalStateException())
    lateinit var cachedLatitude: String
    lateinit var cachedLongitude: String
    lateinit var cachedPictures: List<String>

    private fun GetSampleDetailUseCase.Response.cacheFields() {
        cachedLatitude = address.latitude
        cachedLongitude = address.longitude
        cachedPictures = pictures
    }

    suspend operator fun invoke(state: SampleDetailState): WrappedResult<SampleDetailState> {
        return getSampleDetailUseCase(sampleId)
            .map {
                it.cacheFields()
                it.mapAdDetail(state)
            }
    }
}