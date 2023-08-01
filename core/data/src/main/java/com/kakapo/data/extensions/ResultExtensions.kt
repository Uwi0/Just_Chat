package com.kakapo.data.extensions

fun <T, R> Result<List<T>>.mapList(mapper: (T) -> R): List<R> {
    return try {
        if (isSuccess) {
            val mappedList = getOrThrow().map(mapper)
            mappedList
        } else {
            throw Exception(exceptionOrNull())
        }
    } catch (e: Exception) {
        throw e
    }
}

suspend fun <T> proceed(func: suspend () -> T): T {
    return try {
        func.invoke()
    }catch (e: Exception){
        throw e
    }
}

suspend fun <T> proceedResult(func: suspend () -> Result<T>): T {
    return try {
        val result = func.invoke()
        if (result.isSuccess) {
            result.getOrThrow()
        } else {
            throw Exception(result.exceptionOrNull())
        }
    } catch (e: Exception) {
        throw e
    }
}

