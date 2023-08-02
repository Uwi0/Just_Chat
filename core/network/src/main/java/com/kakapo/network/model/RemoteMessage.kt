package com.kakapo.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteMessage(
    val id: String,
    val text: String,
    val timestamp: Long,
    val username: String
)
