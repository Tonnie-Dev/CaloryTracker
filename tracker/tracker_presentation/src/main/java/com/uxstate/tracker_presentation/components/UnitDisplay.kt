package com.uxstate.tracker_presentation.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UnitDisplay(value:Int, unit:String) {
    
    
    Text(text = "$value $unit")
}
@Preview
@Composable
fun PreviewUnitDisplay() {
    
    UnitDisplay(value = 0, unit = "Kcal")
}