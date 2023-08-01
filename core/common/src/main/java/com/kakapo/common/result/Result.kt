package com.kakapo.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
    object Loading : Result<Nothing>
}

fun <T> Flow<T>?.asResult(): Flow<Result<T>> {
    return this?.map<T, Result<T>> {
        Result.Success(it)
    }
        ?.catch {
            emit(Result.Error(it))
        }
        ?.onStart { emit(Result.Loading) }
        ?: flowOf(Result.Error(NullPointerException("Flow<Result<T>> is null")))
}

suspend fun <T> Flow<Result<T>>.subscribe(
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (Throwable?) -> Unit = {}
) {
    this.collect { result ->
        when (result) {
            Result.Loading -> onLoading.invoke()
            is Result.Error -> {
                onError.invoke(result.exception)
            }
            is Result.Success -> onSuccess.invoke(result.data)
        }
    }
}

suspend fun <T> Flow<Result<T>>.suspendSubscribe(
    onLoading: suspend () -> Unit = {},
    onSuccess: suspend (T) -> Unit = {},
    onError: suspend (Throwable?) -> Unit = {}
) {
    this.collect { result ->
        when (result) {
            Result.Loading -> onLoading.invoke()
            is Result.Error -> {
                onError.invoke(result.exception)
            }
            is Result.Success -> onSuccess.invoke(result.data)
        }
    }
}