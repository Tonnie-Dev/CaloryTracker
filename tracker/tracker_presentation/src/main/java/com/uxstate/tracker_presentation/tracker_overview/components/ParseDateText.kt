package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun ParseDateText(date:LocalDate) {

    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val tomorrow = today.plusDays(1)

}