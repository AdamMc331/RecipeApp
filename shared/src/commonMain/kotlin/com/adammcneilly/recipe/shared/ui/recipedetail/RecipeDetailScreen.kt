package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.recipe.shared.CommonParcelize
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

/**
 * Represents a screen in the app that shows a specific [initialRecipe].
 */
@CommonParcelize
data class RecipeDetailScreen(
    val initialRecipe: RecipeDisplayModel,
) : Screen {
    data class State(
        val recipe: RecipeDisplayModel,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent
}

/**
 * State management for the [RecipeDetailScreen].
 */
class RecipeDetailPresenter(
    private val initialRecipe: RecipeDisplayModel,
    private val navigator: Navigator,
) : Presenter<RecipeDetailScreen.State> {

    @Composable
    override fun present(): RecipeDetailScreen.State {
        return RecipeDetailScreen.State(initialRecipe) {
            // No Op
        }
    }
}

/**
 * Factory to create a [recipeDetailUi] for a given [Screen].
 */
class RecipeDetailScreenUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is RecipeDetailScreen -> {
                val initialRecipe = screen.initialRecipe
                recipeDetailUi(initialRecipe)
            }

            else -> null
        }
    }
}

private fun recipeDetailUi(initialRecipe: RecipeDisplayModel) = ui<RecipeDetailScreen.State> { state, modifier ->
    val stateToUse = state.copy(
        recipe = initialRecipe,
    )

    RecipeDetailContent(
        recipe = stateToUse.recipe,
        modifier = modifier,
    )
}

/**
 * Factory to create a [RecipeDetailPresenter].
 */
class RecipeDetailScreenPresenterFactory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is RecipeDetailScreen -> {
                RecipeDetailPresenter(
                    initialRecipe = screen.initialRecipe,
                    navigator = navigator,
                )
            }
            else -> null
        }
    }
}
