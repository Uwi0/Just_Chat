package com.kakapo.data.repository.impl

import com.kakapo.data.extensions.proceed
import com.kakapo.data.model.param.RegisterParam
import com.kakapo.data.model.param.asParamRequest
import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.data_store.constants.PreferenceKey
import com.kakapo.data_store.datasource.base.PreferenceDataSource
import com.kakapo.network.dataSource.base.RemoteAuthDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource,
    private val remoteDatasource: RemoteAuthDatasource
) : AuthRepository {

    override fun authenticateUserRegister(param: RegisterParam): Flow<Unit> = flow {
        emit(
            proceed {
                remoteDatasource.signUpUser(param.asParamRequest())
            }
        )
    }

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