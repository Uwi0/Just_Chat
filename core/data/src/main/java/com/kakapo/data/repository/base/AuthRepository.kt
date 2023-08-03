package com.kakapo.data.repository.base

import com.kakapo.data.model.param.RegisterParam
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun authenticateUserRegister(param: RegisterParam): Flow<Unit>

    fun authenticateUserLogin(username: String): Flow<Unit>

    fun getSavedUsername(): Flow<String>
}