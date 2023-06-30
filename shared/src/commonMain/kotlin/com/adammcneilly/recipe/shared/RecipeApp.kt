package com.adammcneilly.recipe.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator

@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
) {
    val circuitConfig = CircuitConfig.Builder()
        .addUiFactory(RecipeListScreenUiFactory())
        .addUiFactory(RecipeDetailScreenUiFactory())
        .addPresenterFactory(RecipeListScreenPresenterFactory())
        .addPresenterFactory(RecipeDetailScreenPresenterFactory())
        .build()

    CircuitCompositionLocals(circuitConfig) {
        val backstack = rememberSaveableBackStack {
            push(RecipeListScreen)
        }

        val navigator = rememberCircuitNavigator(backstack) {
            // onRootPop?
        }

        NavigableCircuitContent(
            navigator = navigator,
            backstack = backstack,
            modifier = modifier,
        )
    }
}
