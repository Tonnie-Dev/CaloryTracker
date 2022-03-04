package com.uxstate.tracker_data.di

import android.content.Context
import androidx.room.Room
import com.uxstate.tracker_data.local.TrackerDao
import com.uxstate.tracker_data.local.db.TrackerDatabase
import com.uxstate.tracker_data.remote.OpenFoodAPI
import com.uxstate.tracker_data.repository.TrackerRepositoryImpl
import com.uxstate.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    /*Add interceptor to log our network requests
    *
    * This is helpful in debugging*/

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodAPI(client: OkHttpClient): OpenFoodAPI {
        return Retrofit.Builder()
                .baseUrl(OpenFoodAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(OpenFoodAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(@ApplicationContext context: Context):TrackerDatabase {

        return Room.databaseBuilder(context, TrackerDatabase::class.java, "tracker_db").build()
    }

    //instead of the dao, pass in a db which helps in testing using in-memory db
    @Provides
    @Singleton
    fun provideTrackerRepository(db:TrackerDatabase, api: OpenFoodAPI):TrackerRepository{

        return TrackerRepositoryImpl(db.dao, api)
    }
}