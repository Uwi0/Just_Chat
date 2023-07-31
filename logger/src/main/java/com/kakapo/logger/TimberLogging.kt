package com.kakapo.logger

import timber.log.Timber

class TimberLogging : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return if (BuildConfig.DEBUG_MODE) "(${element.fileName}:${element.lineNumber}) on ${element.methodName} nyaaa~~~ " else ""
    }
}