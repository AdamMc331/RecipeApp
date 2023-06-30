package com.adammcneilly.recipe.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.CircuitContent

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
) {
    val circuitConfig = CircuitConfig.Builder()
        .addUiFactory(RecipeListScreenUiFactory())
        .addPresenterFactory(RecipeListScreenPresenterFactory())
        .build()

    CircuitCompositionLocals(circuitConfig) {
        CircuitContent(
            screen = RecipeListScreen,
            modifier = modifier,
        )
    }
}
