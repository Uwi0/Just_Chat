package com.kakapo.network.di

import com.kakapo.network.dataSource.base.RemoteChatSocketDatasource
import com.kakapo.network.dataSource.base.RemoteMessageDatasource
import com.kakapo.network.dataSource.impl.RemoteChatSocketDatasourceImpl
import com.kakapo.network.dataSource.impl.RemoteMessageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDatasourceModule {

    @Binds
    fun bindMessageDatasource(
        impl: RemoteMessageDataSourceImpl
    ): RemoteMessageDatasource

    @Binds
    fun bindChatSocketDatasource(
        impl: RemoteChatSocketDatasourceImpl
    ): RemoteChatSocketDatasource
}