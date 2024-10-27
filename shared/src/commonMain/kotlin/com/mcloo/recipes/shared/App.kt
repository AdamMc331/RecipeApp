package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import com.mcloo.recipes.shared.recipelist.RecipeListScreen
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        CircuitCompositionLocals(circuitConfig) {
            val backStack = rememberSaveableBackStack(root = RecipeListScreen)

            val navigator = provideCircuitNavigator(
                backStack = backStack,
                onRootPop = { result ->
                    // TODO: Handle result
                    println("ADAMLOG - onRootPop: $result")
                },
            )

            NavigableCircuitContent(
                navigator = navigator,
                backStack = backStack,
            )
        }
    }
}
