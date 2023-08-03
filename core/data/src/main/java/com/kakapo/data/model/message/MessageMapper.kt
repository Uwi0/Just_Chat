package com.kakapo.data.model.message

import com.kakapo.model.chat.Message
import com.kakapo.network.model.response.ResponseMessage

fun toMessage(entity: ResponseMessage): Message {
    return Message(
        id = entity.id,
        content = entity.text,
        timestamp = "${entity.timestamp}",
        username = entity.username,
        imageProfileUrl = ""
    )
}