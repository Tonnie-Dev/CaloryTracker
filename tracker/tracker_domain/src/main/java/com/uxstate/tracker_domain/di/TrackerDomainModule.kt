package com.uxstate.tracker_domain.di

import com.uxstate.tracker_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//use cases should be scoped to ViewModel scope as they are used by the ViewModels
@Module
@InstallIn(ViewModelComponent::class)

object TrackerDomainModule {

    @Provides
    @ViewModelScoped

    fun provideTrackerUseCases(
        calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
        deleteFoodUseCase: DeleteFoodUseCase,
        getFoodsForDateUseCase: GetFoodsForDateUseCase,
        searchFoodUseCase: SearchFoodUseCase,
        trackFoodUseCase: TrackFoodUseCase
    ): TrackerUseCases {

        return TrackerUseCases(
            calculateMealNutrientsUseCase = calculateMealNutrientsUseCase,
            deleteFoodUseCase = deleteFoodUseCase,
            getFoodsForDateUseCase = getFoodsForDateUseCase,
            searchFoodUseCase = searchFoodUseCase,
            trackFoodUseCase = trackFoodUseCase

        )
    }
}