package com.kakapo.network.dataSource.base

import com.kakapo.network.model.RemoteMessage

interface RemoteMessageDataSource {

    suspend fun getAllMessages(): Result<List<RemoteMessage>>

}