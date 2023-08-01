package com.kakapo.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val justChatDispatcher: JustChatDispatchers)

enum class JustChatDispatchers{
    IO
}