package com.kakapo.network.dataSource.base

import com.kakapo.network.model.request.RequestAuth

interface RemoteAuthDatasource {

    suspend fun signUpUser(signUp: RequestAuth)

}