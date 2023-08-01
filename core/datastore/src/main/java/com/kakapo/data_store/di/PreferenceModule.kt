package com.kakapo.data_store.di

import com.kakapo.data_store.datasource.base.PreferenceDataSource
import com.kakapo.data_store.datasource.impl.PreferenceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PreferenceModule {

    @Binds
    fun bindsPreferenceDataSource(
        datasource: PreferenceDataSourceImpl
    ): PreferenceDataSource
}