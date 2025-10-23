package com.bagmanovam.thousand_courses.core.data

import retrofit2.Response
import com.bagmanovam.thousand_courses.core.domain.NetworkError
import com.bagmanovam.thousand_courses.core.domain.Result

inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> {
    return when (response.code()) {
        in 200..299 -> {
            try {
                val bd = response.body()
                if (bd != null)
                    Result.Success(bd)
                else
                    Result.Error(NetworkError.SERVER_ERROR)
            } catch (e: Exception) {
                Result.Error(NetworkError.SERVER_ERROR)
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}