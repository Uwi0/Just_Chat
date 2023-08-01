package com.kakapo.data.model.message

import com.kakapo.model.chat.Message
import com.kakapo.network.model.RemoteMessage

fun toMessage(entity: RemoteMessage): Message {
    return Message(
        id = entity.id,
        content = entity.text,
        timestamp = "${entity.timeStamp}",
        username = entity.username,
        imageProfileUrl = ""
    )
}