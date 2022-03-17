package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.uxstate.tracker_presentation.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun parseDateText(date:LocalDate):String {

    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val tomorrow = today.plusDays(1)

    return when(date){
//these are composable invocations therefore the function is marked with @composable
        today -> stringResource(id = R.string.today)
        yesterday -> stringResource(id = R.string.yesterday)
        tomorrow -> stringResource(id = R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }

}