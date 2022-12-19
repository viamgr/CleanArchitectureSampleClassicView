package com.cleansample.feature.detail.presentation

import com.cleansample.core.WrappedResult
import com.cleansample.core.errors.UnknownError
import com.cleansample.core.getOrThrow
import com.cleansample.feature.sample.presentation.IMAGE_SIZE_KEY
import com.cleansample.feature.sample.presentation.IMAGE_SIZE_LARGE
import com.cleansample.feature.sample.presentation.detail.GetSampleDetailResponse
import com.cleansample.feature.sample.presentation.detail.SampleDetailSideEffect
import com.cleansample.feature.sample.presentation.detail.SampleDetailState
import com.cleansample.feature.sample.presentation.detail.SampleDetailViewModel
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.orbitmvi.orbit.test

internal class SampleDetailViewModelFuncSpec : FunSpec() {

    private lateinit var target: SampleDetailViewModel
    private val getSampleDetailResponse: GetSampleDetailResponse = mockk(relaxed = true)
    private val initialResponse = WrappedResult.success(SampleDetailState())

    override fun isolationMode() = IsolationMode.InstancePerTest

    private fun createViewModel() {
        coEvery { getSampleDetailResponse.invoke(any()) } returns initialResponse

        target = SampleDetailViewModel(
            getSampleDetailResponse = getSampleDetailResponse,
        )
    }

    init {
        createViewModel()

        test("call load sample detail intent on success result should change view state") {

            val fakeResult = initialResponse.getOrThrow().copy(description = "3")
            coEvery { getSampleDetailResponse.invoke(any()) } returns WrappedResult.success(
                fakeResult)

            val testSubject = target.test()
            testSubject.testIntent {
                loadSampleDetailIntent()
            }
            testSubject.assert(initialResponse.getOrThrow()) {
                states(
                    { initialResponse.getOrThrow().copy() },
                    { fakeResult.copy(shareButtonVisibility = true, isLoading = false) },
                )
            }
        }


        test("call on load sample detail intent on error result should set error state") {
            val initialState = SampleDetailState()

            coEvery { getSampleDetailResponse.invoke(any()) } returns WrappedResult.failure(
                Exception())

            val testSubject = target.test()
            testSubject.testIntent {
                loadSampleDetailIntent()
            }
            testSubject.assert(initialState) {
                states(
                    { copy(isLoading = true) },
                    { copy(isLoading = false, error = UnknownError) },
                )
            }
        }


        test("call load sample detail event should get sample and map success result") {
            coVerify(exactly = 1) {
                getSampleDetailResponse.invoke(any())
            }
        }

        test("on document click listener event should call open document effect") {
            val testSubject = target.test()
            val expectedDocumentUrl = "expectedUrl"

            testSubject.testIntent {
                onDocumentClickListenerEvent(expectedDocumentUrl)
            }

            testSubject.assert(SampleDetailState()) {
                postedSideEffects(
                    SampleDetailSideEffect.OpenDocument(expectedDocumentUrl),
                )
            }
        }


        test("on carousel item clicked listener event should open picture screen with correct url") {

            val imageUrl = "AAA${IMAGE_SIZE_KEY}AAA"
            val pictures = listOf("0000{imageId}000", imageUrl, "12222{imageId}222")

            val expectedResult = "AAA${IMAGE_SIZE_LARGE}AAA"

            coEvery { getSampleDetailResponse.cachedPictures } returns pictures

            val testSubject = target.test()
            testSubject.testIntent {
                onCarouselItemClickedListenerEvent(1)
            }

            testSubject.assert(SampleDetailState()) {
                postedSideEffects(
                    SampleDetailSideEffect.OpenPictureScreen(expectedResult),
                )
            }
        }
    }
}