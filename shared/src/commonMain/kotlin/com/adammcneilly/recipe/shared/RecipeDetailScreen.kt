package com.adammcneilly.recipe.shared

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
data class RecipeDetailScreen(
    val initialRecipe: String,
) : Screen {
    data class State(
        val recipe: String,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        /**
         * Go back to the previous screen.
         */
        object GoBack : Event
    }
}

/**
 * State management for the [RecipeDetailScreen].
 */
class RecipeDetailPresenter(
    private val navigator: Navigator,
) : Presenter<RecipeDetailScreen.State> {

    @Composable
    override fun present(): RecipeDetailScreen.State {
        return RecipeDetailScreen.State("") { event ->
            when (event) {
                RecipeDetailScreen.Event.GoBack -> {
                    navigator.pop()
                }
            }
        }
    }
}

/**
 * This is the actual UI to render inside a [RecipeDetailScreen].
 */
@Composable
fun RecipeDetailContent(
    state: RecipeDetailScreen.State,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Recipe Detail For: ${state.recipe}",
        modifier = modifier,
    )
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

private fun recipeDetailUi(initialRecipe: String) = ui<RecipeDetailScreen.State> { state, modifier ->
    val stateToUse = state.copy(
        recipe = initialRecipe,
    )

    RecipeDetailContent(stateToUse, modifier)
}

/**
 * Factory to create a [RecipeDetailPresenter].
 */
class RecipeDetailScreenPresenterFactory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is RecipeDetailScreen -> RecipeDetailPresenter(navigator)
            else -> null
        }
    }
}
