package com.cleansample.feature.sample.presentation.detail

import com.cleansample.core.errors.BaseError
import com.cleansample.feature.sample.presentation.model.Attribute
import com.cleansample.feature.sample.presentation.model.Document
import com.cleansample.feature.sample.presentation.model.Summary
import com.cleansample.feature.sample.presentation.pattern.State

data class SampleDetailState(
    val shareButtonVisibility: Boolean = false,
    val description: String = "",
    val documents: List<Document> = emptyList(),
    val summary: Summary = Summary(),
    val attributes: List<Attribute> = emptyList(),
    val features: List<String> = emptyList(),
    val pictures: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val error: BaseError? = null,
) : State