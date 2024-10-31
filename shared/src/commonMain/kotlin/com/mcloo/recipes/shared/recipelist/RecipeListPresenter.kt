package com.mcloo.recipes.shared.recipelist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mcloo.recipes.shared.data.RecipeService
import com.mcloo.recipes.shared.recipedetail.RecipeDetailScreen
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import com.mcloo.recipes.shared.useDebounce
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class RecipeListPresenter(
    private val recipeService: RecipeService,
    private val navigator: Navigator,
) : Presenter<RecipeListScreen.State> {
    @Composable
    override fun present(): RecipeListScreen.State {
        var searchQuery by remember { mutableStateOf("") }
        var recipes by remember { mutableStateOf(emptyList<RecipeSummaryDisplayModel>()) }

        searchQuery.useDebounce { query ->
            // TODO: Add error handling
            recipeService.getRecipesByName(query).onSuccess { result ->
                recipes = result.map(::RecipeSummaryDisplayModel)
            }
        }

        return RecipeListScreen.State(
            searchQuery = searchQuery,
            recipes = recipes,
        ) { event ->
            when (event) {
                is RecipeListScreen.Event.RecipeClicked -> {
                    val screen = RecipeDetailScreen(event.id)
                    navigator.goTo(screen)
                }

                is RecipeListScreen.Event.SearchQueryChanged -> {
                    searchQuery = event.searchQuery
                }
            }
        }
    }
}
