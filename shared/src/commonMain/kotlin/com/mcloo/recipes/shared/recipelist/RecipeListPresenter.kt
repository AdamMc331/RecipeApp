package com.mcloo.recipes.shared.recipelist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.mcloo.recipes.shared.data.RecipeService
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class RecipeListPresenter(
    private val recipeService: RecipeService,
    private val navigator: Navigator,
) : Presenter<RecipeListScreen.State> {
    @Composable
    override fun present(): RecipeListScreen.State {
        var recipes by rememberSaveable { mutableStateOf(emptyList<RecipeSummaryDisplayModel>()) }

        LaunchedEffect(Unit) {
            recipeService.getRecipesByName("chicken").onSuccess { result ->
                recipes = result.map(::RecipeSummaryDisplayModel)
            }
        }

        return RecipeListScreen.State(
            recipes = recipes,
        ) { event ->
            when (event) {
                is RecipeListScreen.Event.RecipeClicked -> TODO()
            }
        }
    }
}
