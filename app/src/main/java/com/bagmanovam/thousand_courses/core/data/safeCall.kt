package com.bagmanovam.thousand_courses.core.data

import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import retrofit2.Response
import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.channels.UnresolvedAddressException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> Response<T>
): Result<T, NetworkError> {
    val response = try {
        execute.invoke()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: UnknownHostException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        return Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: ConnectException) {
        return Result.Error(NetworkError.CONNECTION_ERROR)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}