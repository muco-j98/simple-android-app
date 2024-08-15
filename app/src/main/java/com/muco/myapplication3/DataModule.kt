package com.muco.myapplication3

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.openbrewerydb.org/v1/")
        .build()

    @Singleton
    @Provides
    fun provideBreweryApi(retrofit: Retrofit) = retrofit.create(BreweryApi::class.java)
}