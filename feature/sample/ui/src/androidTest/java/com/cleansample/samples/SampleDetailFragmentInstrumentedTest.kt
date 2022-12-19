package com.cleansample.samples

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cleansample.android_test_shared.*
import com.cleansample.android_test_shared.TextColorMatcher.Companion.withTextColor
import com.cleansample.core.WrappedResult
import com.cleansample.feature.sample.presentation.detail.GetSampleDetailResponse
import com.cleansample.feature.sample.presentation.detail.SampleDetailState
import com.cleansample.feature.sample.presentation.detail.SampleDetailViewModel
import com.cleansample.feature.sample.presentation.model.Document
import com.cleansample.feature.sample.presentation.model.Summary
import com.cleansample.feature.sample.ui.R
import com.cleansample.feature.sample.ui.detail.SampleDetailFragment
import com.cleansample.ui_theme.R.color
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SampleDetailFragmentInstrumentedTest {
    @BindValue
    lateinit var viewModel: SampleDetailViewModel

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun prepareTest() {
        hiltRule.inject()

    }

    private fun fakeState(fakeState: SampleDetailState): GetSampleDetailResponse {
        val getSampleDetailResponse = mockk<GetSampleDetailResponse>(relaxed = true).also {
            coEvery { it.invoke(any()) } returns WrappedResult.success(fakeState)
        }
        return getSampleDetailResponse
    }

    @Test
    fun onSummaryFilled_textsShouldBeDisplayed() {

        val badString = "!Erstbezug! Dachgeschosswohnung mit Bodenheizung"
        val visits = 2222
        val fakeState = fakeState(SampleDetailState(
            summary = Summary(
                title = badString,
                date = badString,
                visits = visits,
                id = badString
            )
        ))

        viewModel = SampleDetailViewModel(getSampleDetailResponse = fakeState)

        launchFragmentInHiltContainer<SampleDetailFragment>()

        checkText(R.id.view_holder_summary_title, badString)
        checkText(R.id.view_holder_summary_date, badString)
        checkText(R.id.view_holder_summary_visits, visits.toString())
        checkText(R.id.view_holder_summary_id,
            getContext().getString(R.string.sample_id, badString))

    }

    @Test
    fun onEmptyState_noViewsShouldBeVisible() {

        val fakeState = fakeState(SampleDetailState())

        viewModel = SampleDetailViewModel(getSampleDetailResponse = fakeState)

        launchFragmentInHiltContainer<SampleDetailFragment>()

        checkDoesNotExist(R.id.view_holder_description)
        checkDoesNotExist(R.id.view_holder_document)
        checkDoesNotExist(R.id.view_holder_error)
        checkDoesNotExist(R.id.view_holder_loading)
        checkDoesNotExist(R.id.view_holder_photo)
        checkDoesNotExist(R.id.view_holder_photo)
        checkDoesNotExist(R.id.view_holder_specification)
        checkDoesNotExist(R.id.wrapper_carousel)
        checkDoesNotExist(R.id.wrapper_feature_list)
    }

    @Test
    fun onEmptyValidPriceValues_textAppearanceShouldBeDisplayedFine() {

        val price = "5555.45"
        val currency = "AFakeCurrency"
        val expectedText = "$price $currency"

        val fakeState = fakeState(SampleDetailState(summary = Summary(
            price = price,
            currency = currency
        )))

        viewModel = SampleDetailViewModel(getSampleDetailResponse = fakeState)

        launchFragmentInHiltContainer<SampleDetailFragment> {

        }

        onView(withText(expectedText)).check(matches(withTextColor(color.green_500,
            getContext())))

        checkExistWithText(expectedText)

    }

    @Test
    fun onValidAddressState_addressTextShouldShowCorrectData() {

        val getSampleDetailResponse = fakeState(SampleDetailState(summary = Summary(
            street = "street",
            zipCode = "zipCode",
            city = "city",
        )))

        viewModel =
            SampleDetailViewModel(getSampleDetailResponse = getSampleDetailResponse)

        launchFragmentInHiltContainer<SampleDetailFragment>()

        with(onView(withId(R.id.view_holder_summary_address))) {
            check(matches(isDisplayed()))
            check(matches(withText("street, zipCode, city")))
        }
    }

    @Test
    fun onSpecificDocumentListSize_allShouldBeDisplayedProperly() {

        val getSampleDetailResponse = fakeState(SampleDetailState(
            documents = listOf(
                Document(link = "", title = "documentTitle1"),
                Document(link = "", title = "documentTitle2"),
                Document(link = "", title = "documentTitle3"),
            )))

        viewModel =
            SampleDetailViewModel(getSampleDetailResponse = getSampleDetailResponse)

        launchFragmentInHiltContainer<SampleDetailFragment>()
        checkExistWithText("documentTitle1")
        checkExistWithText("documentTitle2")
        checkExistWithText("documentTitle3")
    }

}
