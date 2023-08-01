package com.kakapo.network.dataSource.base

import com.kakapo.network.model.RemoteMessage

interface RemoteMessageDatasource {

    suspend fun getAllMessages(): Result<List<RemoteMessage>>

}