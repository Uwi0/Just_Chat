package com.kakapo.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestAuth(
    val username: String,
    val email: String,
    val password: String
)
