package com.adammcneilly.recipe.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = "Hello, Compose FOR REAL THOUGH IS IT WORKING?!",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
