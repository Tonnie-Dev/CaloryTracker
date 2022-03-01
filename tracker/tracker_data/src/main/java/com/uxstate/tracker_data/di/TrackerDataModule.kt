package com.uxstate.tracker_data.di

import com.squareup.moshi.Moshi
import com.uxstate.tracker_data.remote.OpenFoodAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton

    fun provideOpenFoodAPI(client:OkHttpClient) :OpenFoodAPI{


        return Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(OpenFoodAPI::class.java)
    }
}