package com.adammcneilly.recipe.shared

import androidx.compose.foundation.clickable
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
 * Screen to show a list of recipes.
 */
object RecipeListScreen : Screen {
    data class RecipeListState(
        val recipes: List<String>,
        val eventSink: (RecipeListEvent) -> Unit = {},
    ) : CircuitUiState

    sealed interface RecipeListEvent : CircuitUiEvent {
        data class RecipeClicked(
            val recipe: String,
        ) : RecipeListEvent
    }
}

class RecipeListPresenter(
    private val navigator: Navigator,
) : Presenter<RecipeListScreen.RecipeListState> {

    @Composable
    override fun present(): RecipeListScreen.RecipeListState {
        return RecipeListScreen.RecipeListState(emptyList()) { event ->
            when (event) {
                is RecipeListScreen.RecipeListEvent.RecipeClicked -> {
                    navigator.goTo(RecipeDetailScreen(event.recipe))
                }
            }
        }
    }
}

@Composable
fun RecipeListContent(
    state: RecipeListScreen.RecipeListState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Recipe List UI",
        modifier = modifier
            .clickable {
                state.eventSink.invoke(
                    RecipeListScreen.RecipeListEvent.RecipeClicked("Test Recipe"),
                )
            },
    )
}

class RecipeListScreenUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            RecipeListScreen -> recipeListUi()
            else -> null
        }
    }
}

private fun recipeListUi() = ui<RecipeListScreen.RecipeListState> { state, modifier -> RecipeListContent(state, modifier) }

class RecipeListScreenPresenterFactory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is RecipeListScreen -> RecipeListPresenter(navigator)
            else -> null
        }
    }
}
