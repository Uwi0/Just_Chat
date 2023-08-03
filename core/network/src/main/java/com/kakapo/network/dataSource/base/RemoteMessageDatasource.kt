package com.kakapo.network.dataSource.base

import com.kakapo.network.model.response.ResponseMessage

interface RemoteMessageDatasource {

    suspend fun getAllMessages(): Result<List<ResponseMessage>>

}