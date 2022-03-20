package com.uxstate.tracker_domain.di

import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.repository.TrackerRepository
import com.uxstate.tracker_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


//use cases should be scoped to ViewModel scope as they are used by the ViewModels
@Module
@InstallIn(ViewModelComponent::class)

object TrackerDomainModule {

    @Provides
    @ViewModelScoped

    //tracker repository already provided on the TrackerModule
    fun provideTrackerUseCases(
       repository: TrackerRepository, preferences: Preferences
    ): TrackerUseCases {

        return TrackerUseCases(
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(prefs =preferences),
            deleteFoodUseCase = DeleteFoodUseCase(repository),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(repository),
            searchFoodUseCase = SearchFoodUseCase(repository),
            trackFoodUseCase = TrackFoodUseCase(repository)

        )
    }
}