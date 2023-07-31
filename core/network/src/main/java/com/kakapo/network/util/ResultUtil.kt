package com.kakapo.network.util

import com.kakapo.common.exception.UnexpectedException
import com.kakapo.common.exception.networkException.ApiException
import com.kakapo.common.exception.networkException.NoInternetException
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeNetworkCall(callNetwork: suspend () -> T): Result<T>{
    return try {
        val result = callNetwork.invoke()
        Result.success(result)
    }catch (t: Throwable){
        val exception = assignException(t)
        Result.failure(exception)
    }
}

fun <T> flowNetworkCall(callNetwork: () -> Flow<T>): Flow<T>{
    return try {
        callNetwork.invoke()
    }catch (t: Throwable){
        val exception = assignException(t)
        throw exception
    }
}

private fun assignException(t: Throwable) = when (t) {
    is IOException -> {
        NoInternetException()
    }

    is HttpException -> {
        ApiException(t.message())
    }

    else -> {
        UnexpectedException(t.message ?: "Error", t)
    }
}
