package com.kakapo.model.chat

data class Message(
    val id: String,
    val content: String,
    val timestamp: String,
    val username: String,
    val imageProfileUrl: String = ""
)
