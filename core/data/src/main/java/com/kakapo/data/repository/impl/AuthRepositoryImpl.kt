package com.kakapo.data.repository.impl

import com.kakapo.data.extensions.proceed
import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.data_store.constants.PreferenceKey
import com.kakapo.data_store.datasource.base.PreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : AuthRepository {

    override fun authenticateUserLogin(username: String): Flow<Unit> = flow {
        emit(
            proceed {
                preferenceDataSource.saveStringValue(PreferenceKey.SAVED_USERNAME, username)
            }
        )
    }

    override fun getSavedUsername(): Flow<String> = flow {
        emit(
            proceed {
                preferenceDataSource.getStringValue(PreferenceKey.SAVED_USERNAME)
            }
        )
    }
}