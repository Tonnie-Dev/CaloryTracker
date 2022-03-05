package com.plcoding.calorytracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import com.uxstate.core.data.preferences.DefaultPreferences
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.onboarding_domain.use_case.ValidateNutrients
import com.uxstate.tracker_domain.repository.TrackerRepository
import com.uxstate.tracker_domain.use_cases.DeleteFoodUseCase
import com.uxstate.tracker_domain.use_cases.GetFoodsForDateUseCase
import com.uxstate.tracker_domain.use_cases.SearchFoodUseCase
import com.uxstate.tracker_domain.use_cases.TrackFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {


        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase():FilterOutDigits{

        return  FilterOutDigits()
    }


    @Provides
    @Singleton
   fun provideDeleteFoodUseCase(repository: TrackerRepository): DeleteFoodUseCase {


       return DeleteFoodUseCase(repository = repository)
   }

    @Provides
    @Singleton
    fun provideGetFoodsForDateUseCase(repository: TrackerRepository): GetFoodsForDateUseCase {


        return GetFoodsForDateUseCase(repository = repository)
    }


    @Provides
    @Singleton
    fun provideSearchFoodUseCase(repository: TrackerRepository): SearchFoodUseCase {


        return SearchFoodUseCase(repository = repository)
    }


    @Provides
    @Singleton
    fun provideTrackFoodUseCase(repository: TrackerRepository): TrackFoodUseCase {


        return TrackFoodUseCase(repository = repository)
    }


}