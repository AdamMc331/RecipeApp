package com.mcloo.recipes.shared.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Float.convertToDp(): Dp {
    return with(LocalDensity.current) {
        this@convertToDp.toDp()
    }
}
