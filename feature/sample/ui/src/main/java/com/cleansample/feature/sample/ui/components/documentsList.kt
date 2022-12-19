package com.cleansample.feature.sample.ui.components

import com.airbnb.epoxy.ModelCollector
import com.cleansample.feature.sample.presentation.model.Document
import com.cleansample.feature.sample.ui.document

internal fun ModelCollector.documentsList(
    documents: List<Document>,
    onDocumentClickListener: (String) -> Unit,
) {
    for (document in documents) {
        document {
            id("document", document.link, document.title)
            label(document.title)
            onItemClickListener {
                onDocumentClickListener(document.link)
            }
        }
        divider()
    }
}
