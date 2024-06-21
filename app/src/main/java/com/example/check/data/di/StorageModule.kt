package com.example.check.data.di

import com.example.check.data.local.storage.AuthDataStorage
import com.example.check.data.local.storage.AuthDataStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun provideStorageDataStorage(
        authDataStorageImpl: AuthDataStorageImpl
    ): AuthDataStorage

}