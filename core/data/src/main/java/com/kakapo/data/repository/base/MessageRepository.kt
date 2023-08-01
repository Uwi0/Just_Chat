package com.kakapo.data.repository.base

import com.kakapo.model.chat.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    fun getAllMessages(): Flow<List<Message>>
}