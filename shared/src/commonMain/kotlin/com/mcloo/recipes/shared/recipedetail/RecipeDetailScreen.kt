package com.mcloo.recipes.shared.recipedetail

import com.mcloo.recipes.shared.Parcelize
import com.mcloo.recipes.shared.data.mealdb.MealDBRecipeService
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel
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
data class RecipeDetailScreen(
    val recipeId: String,
) : Screen {
    data class State(
        val recipe: RecipeDetailDisplayModel?,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent

    object UiFactory : Ui.Factory {
        override fun create(
            screen: Screen,
            context: CircuitContext,
        ): Ui<*>? {
            return when (screen) {
                is RecipeDetailScreen -> {
                    ui<State> { state, modifier ->
                        RecipeDetailContent(
                            recipe = state.recipe,
                            modifier = modifier,
                        )
                    }
                }

                else -> {
                    null
                }
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
                is RecipeDetailScreen -> RecipeDetailPresenter(
                    recipeId = screen.recipeId,
                    recipeService = recipeService,
                )

                else -> null
            }
        }
    }
}
