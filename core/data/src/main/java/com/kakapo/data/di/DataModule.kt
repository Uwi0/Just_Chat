package com.kakapo.data.di

import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.data.repository.base.ChatSocketRepository
import com.kakapo.data.repository.base.MessageRepository
import com.kakapo.data.repository.impl.AuthRepositoryImpl
import com.kakapo.data.repository.impl.ChatSocketRepositoryImpl
import com.kakapo.data.repository.impl.MessageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindAuthRepository(
        repository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsChatSocketRepository(
        repository: ChatSocketRepositoryImpl
    ): ChatSocketRepository

    @Binds
    fun bindMessageRepository(
        repository: MessageRepositoryImpl
    ): MessageRepository
}