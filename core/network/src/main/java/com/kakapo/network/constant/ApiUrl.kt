package com.kakapo.network.constant

object ApiUrl {

    private const val BASE_URL = "http://192.168.1.69:8080"
    private const val WEB_SOCKET_BASE_URL = "ws://192.168.1.69:8080"

    const val CHAT_SOCKET = "$WEB_SOCKET_BASE_URL/chat-socket"
    const val GET_ALL_MESSAGE = "$BASE_URL/messages"

    //Auth api
    const val SIGN_UP = "$BASE_URL/signup"
    const val SIGN_IN = "$BASE_URL/signin"
    const val AUTHENTICATE = "$BASE_URL/authenticate"
    const val SECRET = "$BASE_URL/secret"
}