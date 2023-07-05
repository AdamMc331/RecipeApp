package com.adammcneilly.recipe.shared.ui.recipelist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.CommonParcelize
import com.adammcneilly.recipe.shared.RecipeDetailScreen
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
 * Screen to show a list of recipes.
 */
@CommonParcelize
object RecipeListScreen : Screen {
    data class RecipeListState(
        val recipes: List<RecipeDisplayModel>,
        val eventSink: (RecipeListEvent) -> Unit = {},
    ) : CircuitUiState

    sealed interface RecipeListEvent : CircuitUiEvent {
        data class RecipeClicked(
            val recipe: RecipeDisplayModel,
        ) : RecipeListEvent
    }
}

class RecipeListPresenter(
    private val navigator: Navigator,
) : Presenter<RecipeListScreen.RecipeListState> {

    @Composable
    @Suppress("MagicNumber")
    override fun present(): RecipeListScreen.RecipeListState {
        // Here is where we would make a request to get the data we want
        // let's hardcode for now.
        val recipeList = (1..15).map { index ->
            RecipeDisplayModel(
                timeFrame = "30 Minutes",
                name = "Recipe $index",
            )
        }

        return RecipeListScreen.RecipeListState(recipeList) { event ->
            when (event) {
                is RecipeListScreen.RecipeListEvent.RecipeClicked -> {
                    navigator.goTo(RecipeDetailScreen(event.recipe.name))
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
    RecipeList(
        recipes = state.recipes,
        modifier = modifier,
        onRecipeClicked = { recipe ->
            state.eventSink.invoke(
                RecipeListScreen.RecipeListEvent.RecipeClicked(recipe),
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

private fun recipeListUi() = ui<RecipeListScreen.RecipeListState> { state, modifier ->
    RecipeListContent(state, modifier)
}

class RecipeListScreenPresenterFactory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is RecipeListScreen -> RecipeListPresenter(navigator)
            else -> null
        }
    }
}
