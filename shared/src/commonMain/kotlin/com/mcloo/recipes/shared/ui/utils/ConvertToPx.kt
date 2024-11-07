package com.mcloo.recipes.shared.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.convertToPx(): Float {
    return with(LocalDensity.current) {
        this@convertToPx.roundToPx().toFloat()
    }
}
