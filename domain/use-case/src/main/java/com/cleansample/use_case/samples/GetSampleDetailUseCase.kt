package com.cleansample.use_case.samples

import com.cleansample.core.IoDispatcher
import com.cleansample.core.WrappedResult
import com.cleansample.core.WrappedResult.Companion.map
import com.cleansample.domain.entity.SampleEntity
import com.cleansample.domain.repository.SampleRepository
import com.cleansample.use_case.base.UseCase
import com.cleansample.use_case.mapper.*
import com.cleansample.use_case.model.Address
import com.cleansample.use_case.model.Attribute
import com.cleansample.use_case.model.Document
import com.cleansample.use_case.model.Price
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

class GetSampleDetailUseCase @Inject constructor(
    private val sampleRepository: SampleRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : UseCase<Long, GetSampleDetailUseCase.Response>(coroutineDispatcher) {
    override suspend fun execute(parameter: Long): WrappedResult<Response> {
        return sampleRepository
            .getSample(parameter)
            .map { entity ->
                Response(
                    id = entity.id,
                    description = entity.description,
                    title = entity.title,
                    visits = entity.visits,
                    postedDateTime = mapPostedDateTime(entity),
                    features = entity.features.distinct().mapFeatures(),
                    pictures = entity.pictures.distinct().mapPictures(),
                    attributes = entity.attributes.distinct().mapAttributes(),
                    documents = entity.documents.distinct().mapDocuments(),
                    address = entity.address.mapAddress(),
                    price = entity.price.mapPrice()
                )
            }
    }

    private fun mapPostedDateTime(entity: SampleEntity) = try {
        entity.postedDateTime.mapDate()
    } catch (e: Exception) {
        Timber.e(e)
        "-"
    }

    data class Response(
        val address: Address,
        val attributes: List<Attribute>,
        val description: String,
        val documents: List<Document>,
        val features: List<String>,
        val id: String,
        val pictures: List<String>,
        val postedDateTime: String,
        val price: Price,
        val title: String,
        val visits: Int,
    )

}

