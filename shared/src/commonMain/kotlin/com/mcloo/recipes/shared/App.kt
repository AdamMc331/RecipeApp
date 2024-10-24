package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import com.mcloo.recipes.shared.recipedetail.RecipeDetailScreen
import com.mcloo.recipes.shared.recipelist.RecipeListScreen
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        val circuitConfig = Circuit
            .Builder()
            .addUiFactory(RecipeListScreen.UiFactory)
            .addUiFactory(RecipeDetailScreen.UiFactory)
            .addPresenterFactory(RecipeListScreen.PresenterFactory)
            .addPresenterFactory(RecipeDetailScreen.PresenterFactory)
            .build()

        CircuitCompositionLocals(circuitConfig) {
            val backStack = rememberSaveableBackStack(root = RecipeListScreen)
            val navigator = rememberCircuitNavigator(
                backStack = backStack,
                onRootPop = { result ->
                    // TODO: Handle result
                },
            )

            NavigableCircuitContent(navigator, backStack)
        }
    }
}
