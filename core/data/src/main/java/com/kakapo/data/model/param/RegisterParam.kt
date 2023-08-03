package com.kakapo.data.model.param

import com.kakapo.network.model.request.RequestAuth

data class RegisterParam(
    val username: String,
    val email: String,
    val password: String,
)

fun RegisterParam.asParamRequest(): RequestAuth {
    return RequestAuth(username = username, email = email, password = password)
}
