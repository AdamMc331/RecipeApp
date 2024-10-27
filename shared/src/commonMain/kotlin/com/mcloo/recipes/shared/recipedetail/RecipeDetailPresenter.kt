package com.mcloo.recipes.shared.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mcloo.recipes.shared.data.RecipeService
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel
import com.slack.circuit.runtime.presenter.Presenter

class RecipeDetailPresenter(
    private val recipeId: String,
    private val recipeService: RecipeService,
) : Presenter<RecipeDetailScreen.State> {
    @Composable
    override fun present(): RecipeDetailScreen.State {
        var recipe by remember { mutableStateOf(RecipeDetailDisplayModel.PLACEHOLDER) }

        LaunchedEffect(Unit) {
            recipeService.getRecipeById(recipeId).onSuccess { result ->
                recipe = RecipeDetailDisplayModel(result)
            }
        }

        return RecipeDetailScreen.State(
            recipe = recipe,
        ) { _ ->
        }
    }
}
