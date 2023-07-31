package com.kakapo.network.dataSource.base

import com.kakapo.network.model.RemoteMessage
import kotlinx.coroutines.flow.Flow

interface RemoteChatSocketDataSource {

    suspend fun initSession(username: String): Result<Unit>

    suspend fun sendMessage(message: String): Result<Unit>

    fun observeIncomingMessage(): Flow<RemoteMessage>

    suspend fun closeSession(): Result<Unit>
}