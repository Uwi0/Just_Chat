package com.kakapo.data.repository.base

import com.kakapo.model.chat.Message
import kotlinx.coroutines.flow.Flow

interface ChatSocketRepository {

    fun iniSession(username: String): Flow<Unit>

    fun sendMessage(message: String): Flow<Unit>

    fun observeInComingMessage(): Flow<Message>

    fun closeSession(): Flow<Unit>
}