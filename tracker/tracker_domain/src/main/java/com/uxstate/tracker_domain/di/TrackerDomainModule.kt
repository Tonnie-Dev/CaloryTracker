package com.uxstate.tracker_domain.di

import com.uxstate.tracker_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object TrackerDomainModule {

    @Provides
    @Singleton

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