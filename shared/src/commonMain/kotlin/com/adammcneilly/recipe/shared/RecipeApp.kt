package com.adammcneilly.recipe.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.ui.bars.RecipeTopAppBar
import com.adammcneilly.recipe.shared.ui.recipelist.RecipeListScreen
import com.adammcneilly.recipe.shared.ui.recipelist.RecipeListScreenPresenterFactory
import com.adammcneilly.recipe.shared.ui.recipelist.RecipeListScreenUiFactory
import com.adammcneilly.recipe.shared.ui.theme.RecipeTheme
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeApp(
    modifier: Modifier = Modifier,
) {
    RecipeTheme {
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

            Column(
                modifier = modifier,
            ) {
                RecipeTopAppBar(
                    showBackIcon = (backstack.size > 1),
                    onBackClicked = {
                        navigator.pop()
                    },
                )

                Surface(
                    modifier = Modifier
                        .weight(1F),
                ) {
                    NavigableCircuitContent(
                        navigator = navigator,
                        backstack = backstack,
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }
        }
    }
}
