package com.adammcneilly.recipe.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator

/**
 * This is the main entry point into the application, showing the root screen and handles
 * the most high level navigation support.
 */
@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
) {
    // In the future, we may want to look at some DI framework to make it easy to pass
    // in all of these factories, or even consider the codegen that Circuit offers.
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
            println("Is this being called?")
            // In the future, we need to handle a back press when we are at the root
            // screen (probably just close the app?)
        }

        NavigableCircuitContent(
            navigator = navigator,
            backstack = backstack,
            modifier = modifier,
        )
    }
}
