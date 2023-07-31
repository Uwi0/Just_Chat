package com.kakapo.network.interceptor

import com.kakapo.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger{
    override fun log(message: String) {
        Logger.i(message)
    }
}