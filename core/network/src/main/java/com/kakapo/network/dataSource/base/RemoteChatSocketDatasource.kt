package com.kakapo.network.dataSource.base

import com.kakapo.network.model.response.ResponseMessage
import kotlinx.coroutines.flow.Flow

interface RemoteChatSocketDatasource {

    suspend fun initSession(username: String): Result<Unit>

    suspend fun sendMessage(message: String): Result<Unit>

    fun observeIncomingMessage(): Flow<ResponseMessage>

    suspend fun closeSession(): Result<Unit>
}