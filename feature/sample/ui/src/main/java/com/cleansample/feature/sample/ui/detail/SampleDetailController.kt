package com.cleansample.feature.sample.ui.detail

import com.airbnb.epoxy.EpoxyController
import com.cleansample.core.errors.BaseError
import com.cleansample.feature.sample.presentation.detail.SampleDetailState
import com.cleansample.feature.sample.ui.R
import com.cleansample.feature.sample.ui.components.*
import com.cleansample.feature.sample.ui.loading

internal class SampleDetailController constructor(
    private val onDocumentClickListener: (String) -> Unit,
    private val onRetryClickedListener: () -> Unit,
    private val onCarouselItemClickedListener: (Int) -> Unit,
    private val onShareClickedListener: () -> Unit,
    private val onAddressClickedListener: () -> Unit,
    private val errorMapper: (error: BaseError) -> String,
) : EpoxyController() {
    private lateinit var data: SampleDetailState

    fun submitData(state: SampleDetailState) {
        data = state
        requestModelBuild()
    }


    override fun buildModels() = data.let {
        if (it.isLoading) {
            loading {
                id("loading")
            }

        } else if (it.error != null) {
            val message = errorMapper(it.error!!)
            error(text = message,
                onRetryClickedListener = onRetryClickedListener)

        } else {
            if (it.pictures.isNotEmpty()) {
                carousel(it.pictures,
                    it.shareButtonVisibility,
                    onCarouselItemClickedListener,
                    onShareClickedListener)
            }

            summary(it.summary, onAddressClickedListener)
            largeDivider()

            if (it.attributes.isNotEmpty()) {
                header(R.string.specifications_header)
                specificationList(it.attributes)
                largeDivider()
            }

            if (it.features.isNotEmpty()) {
                header(R.string.equipments_header)
                featureList(it.features)
                largeDivider()
            }

            if (it.documents.isNotEmpty()) {
                header(R.string.documents_header)
                documentsList(it.documents, onDocumentClickListener)
                largeDivider()
            }


            if (it.description.isNotEmpty()) {
                header(R.string.documents_descriptions)
                description(it.description)
            }
        }

    }

}
