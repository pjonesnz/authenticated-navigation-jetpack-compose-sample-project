package com.example.exampleappforgdgpresentation.di

import com.example.exampleappforgdgpresentation.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository = LoginRepository()
}