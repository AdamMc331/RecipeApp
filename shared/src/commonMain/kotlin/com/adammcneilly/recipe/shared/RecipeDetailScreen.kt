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

data class RecipeDetailScreen(
    private val initialRecipe: String,
) : Screen {
    data class RecipeDetailState(
        val recipe: String,
    ) : CircuitUiState

    sealed interface RecipeDetailEvent : CircuitUiEvent {
        // None
    }
}

class RecipeDetailPresenter : Presenter<RecipeDetailScreen.RecipeDetailState> {

    @Composable
    override fun present(): RecipeDetailScreen.RecipeDetailState {
        return RecipeDetailScreen.RecipeDetailState("")
    }
}

@Composable
fun RecipeDetail(
    state: RecipeDetailScreen.RecipeDetailState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Recipe Detail UI",
        modifier = modifier,
    )
}

class RecipeDetailScreenUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            // Look at docs to figure out how to get the initial recipe?
            is RecipeDetailScreen -> recipeDetailUi()
            else -> null
        }
    }
}

private fun recipeDetailUi() = ui<RecipeDetailScreen.RecipeDetailState> { state, modifier ->
    RecipeDetail(state, modifier)
}

class RecipeDetailScreenPresenterFactory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
        return when (screen) {
            is RecipeDetailScreen -> RecipeDetailPresenter()
            else -> null
        }
    }
}
