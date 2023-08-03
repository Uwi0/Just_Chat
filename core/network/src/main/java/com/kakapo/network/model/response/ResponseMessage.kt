package com.kakapo.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMessage(
    val id: String,
    val text: String,
    val timestamp: Long,
    val username: String
)
