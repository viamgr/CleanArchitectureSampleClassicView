package com.cleansample.remote.base

import com.cleansample.core.*
import com.cleansample.core.errors.*
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal abstract class BaseRequest<PARAMS, RESPONSE> {
    abstract suspend fun request(params: PARAMS): WrappedResult<RESPONSE>

    suspend operator fun invoke(params: PARAMS): WrappedResult<RESPONSE> {
        return try {
            request(params)
        } catch (e: UnknownHostException) {
            e.toResult(UnknownHost)
        } catch (e: java.io.IOException) {
            e.toResult(NoInternet)
        } catch (e: TimeoutException) {
            e.toResult(Timeout)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                e.toResult(UnAuthorized)
            } else if (e.code() == 403) {
                e.toResult(ForbiddenError)
            } else {
                e.toResult(ApiError(e.code(), e.message()))
            }
        }
    }
}