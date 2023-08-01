package com.kakapo.data.repository.impl

import com.kakapo.common.di.Dispatcher
import com.kakapo.common.di.JustChatDispatchers
import com.kakapo.data.extensions.proceedResult
import com.kakapo.data.model.message.toMessage
import com.kakapo.data.repository.base.ChatSocketRepository
import com.kakapo.model.chat.Message
import com.kakapo.network.dataSource.base.RemoteChatSocketDatasource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatSocketRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteChatSocketDatasource,
    @Dispatcher(JustChatDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : ChatSocketRepository {
    override fun iniSession(username: String): Flow<Unit> = flow {
        emit(
            proceedResult {
                remoteDatasource.initSession(username)
            }
        )
    }

    override fun sendMessage(message: String): Flow<Unit> = flow {
        emit(
            proceedResult {
                remoteDatasource.sendMessage(message)
            }
        )
    }

    override fun observeInComingMessage(): Flow<Message> {
        return remoteDatasource.observeIncomingMessage().map(::toMessage).flowOn(dispatcher)
    }

    override fun closeSession(): Flow<Unit> = flow {
        emit(
            proceedResult {
                remoteDatasource.closeSession()
            }
        )
    }
}