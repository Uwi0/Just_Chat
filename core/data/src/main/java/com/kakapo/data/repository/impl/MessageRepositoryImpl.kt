package com.kakapo.data.repository.impl

import com.kakapo.data.extensions.proceedResult
import com.kakapo.data.model.message.toMessage
import com.kakapo.data.repository.base.MessageRepository
import com.kakapo.model.chat.Message
import com.kakapo.network.dataSource.base.RemoteMessageDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteMessageDatasource
) : MessageRepository{

    override fun getAllMessages(): Flow<List<Message>> = flow {
        emit(proceedResult(remoteDatasource::getAllMessages).map(::toMessage))
    }
}