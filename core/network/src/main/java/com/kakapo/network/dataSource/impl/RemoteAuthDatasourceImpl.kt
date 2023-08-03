package com.kakapo.network.dataSource.impl

import com.kakapo.network.constant.ApiUrl
import com.kakapo.network.dataSource.base.RemoteAuthDatasource
import com.kakapo.network.model.request.RequestAuth
import com.kakapo.network.util.safeNetworkCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteAuthDatasourceImpl @Inject constructor(
    private val client: HttpClient,
) : RemoteAuthDatasource {

    override suspend fun signUpUser(signUp: RequestAuth) {
        safeNetworkCall {
            client.post(urlString = ApiUrl.SIGN_UP) {
                contentType(ContentType.Application.Json)
                setBody(signUp)
            }

        }
    }
}