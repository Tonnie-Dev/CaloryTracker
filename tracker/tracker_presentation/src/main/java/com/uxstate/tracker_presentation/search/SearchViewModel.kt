package com.uxstate.tracker_presentation.search

import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {
}