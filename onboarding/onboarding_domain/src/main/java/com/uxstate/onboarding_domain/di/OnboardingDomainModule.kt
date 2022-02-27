package com.uxstate.onboarding_domain.di

import com.uxstate.onboarding_domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {


    @Provides
    @ViewModelScoped
    fun provideValidateNutrientsUseCase(): ValidateNutrients {

        return ValidateNutrients()
    }
}