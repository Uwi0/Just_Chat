package com.kakapo.network.dataSource.impl

import com.kakapo.network.constant.ApiUrl
import com.kakapo.network.dataSource.base.RemoteChatSocketDataSource
import com.kakapo.network.model.RemoteMessage
import com.kakapo.network.util.flowNetworkCall
import com.kakapo.network.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteChatSocketDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : RemoteChatSocketDataSource {

    private var socket: WebSocketSession? = null
    override suspend fun initSession(username: String): Result<Unit> {
        return safeNetworkCall {
            socket = client.webSocketSession {
                url("${ApiUrl.CHAT_SOCKET}?username=$username")
            }
            if (socket?.isActive == true) Unit
            else throw Exception("Couldn't establish a connection")
        }
    }

    override suspend fun sendMessage(message: String): Result<Unit> {
        return safeNetworkCall {
            socket?.send(Frame.Text(message))
        }
    }

    override fun observeIncomingMessage(): Flow<RemoteMessage> {
        return flowNetworkCall {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    Json.decodeFromString<RemoteMessage>(json)
                } ?: flowOf()
        }
    }

    override suspend fun closeSession(): Result<Unit> {
        return safeNetworkCall {
            socket?.close()
        }
    }
}