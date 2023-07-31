package com.kakapo.network.constant

object ApiUrl {

    private const val BASE_URL = "ws://192.168.1.69"

    const val CHAT_SOCKET = "$BASE_URL/chat-socket"
    const val GET_ALL_MESSAGE = "$BASE_URL/messages"
}