package com.mcloo.recipes.shared.recipelist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.Parcelize
import com.mcloo.recipes.shared.data.mealdb.MealDBRecipeService
import com.mcloo.recipes.shared.ui.components.RecipeListGrid
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import org.koin.core.component.KoinComponent

@Parcelize
object RecipeListScreen : Screen {
    data class State(
        val recipes: List<RecipeSummaryDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class RecipeClicked(
            val id: String,
        ) : Event
    }

    object UiFactory : Ui.Factory {
        override fun create(
            screen: Screen,
            context: CircuitContext,
        ): Ui<*>? {
            return when (screen) {
                RecipeListScreen -> {
                    ui<State> { state, modifier ->
                        RecipeListGrid(
                            recipes = state.recipes,
                            contentPadding = PaddingValues(16.dp),
                            onRecipeClicked = { id ->
                                state.eventSink(Event.RecipeClicked(id))
                            },
                            modifier = modifier,
                        )
                    }
                }

                else -> null
            }
        }
    }

    object PresenterFactory : Presenter.Factory, KoinComponent {
        // TODO: Inject service instead of hardcode
        private val recipeService = MealDBRecipeService()

        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                RecipeListScreen -> RecipeListPresenter(
                    recipeService = recipeService,
                    navigator = navigator,
                )

                else -> null
            }
        }
    }
}
