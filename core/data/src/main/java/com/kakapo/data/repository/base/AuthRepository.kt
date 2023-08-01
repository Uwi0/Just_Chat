package com.kakapo.data.repository.base

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun authenticateUserLogin(username: String): Flow<Unit>

    fun getSavedUsername(): Flow<String>
}