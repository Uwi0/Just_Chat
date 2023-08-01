package com.kakapo.network.dataSource.impl

import com.kakapo.network.constant.ApiUrl
import com.kakapo.network.dataSource.base.RemoteMessageDatasource
import com.kakapo.network.model.RemoteMessage
import com.kakapo.network.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteMessageDataSourceImpl @Inject constructor(
    private val client: HttpClient
): RemoteMessageDatasource {

    override suspend fun getAllMessages(): Result<List<RemoteMessage>> {
        return safeNetworkCall {
            val response = client.get(urlString = ApiUrl.GET_ALL_MESSAGE)
            response.body<List<RemoteMessage>>()
        }
    }
}