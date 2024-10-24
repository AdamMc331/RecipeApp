package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.mcloo.recipes.shared.data.mealdb.MealDBRecipeService
import com.mcloo.recipes.shared.recipedetail.RecipeDetailContent
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.IngredientDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        var recipe by remember { mutableStateOf<RecipeDetailDisplayModel?>(null) }

        rememberCoroutineScope().launch {
            val remoteRecipe = MealDBRecipeService().getRecipeById("52943")

            recipe = remoteRecipe
                .getOrNull()
                ?.let { recipe ->
                    RecipeDetailDisplayModel(
                        name = recipe.name,
                        image = ImageDisplayModel.Remote(recipe.imageUrl),
                        isFavorite = false,
                        ingredients = recipe.ingredients.map { ingredient ->
                            IngredientDisplayModel(
                                name = ingredient.name,
                                measurement = ingredient.measurement,
                            )
                        },
                    )
                }
        }

        val currentRecipe = recipe

        if (currentRecipe != null) {
            RecipeDetailContent(
                recipe = currentRecipe,
            )
        }
    }
}
