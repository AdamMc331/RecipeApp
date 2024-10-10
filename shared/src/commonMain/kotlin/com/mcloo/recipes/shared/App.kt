package com.mcloo.recipes.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.data.mealdb.MealDBRecipeService
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.mcloo.recipes.shared.ui.components.RecipeListGrid
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        var recipes by remember { mutableStateOf(emptyList<RecipeSummaryDisplayModel>()) }

        rememberCoroutineScope().launch {
            val remoteRecipes = MealDBRecipeService().getRecipesByName("Chicken")

            recipes = remoteRecipes
                .getOrNull()
                ?.map { recipe ->
                    RecipeSummaryDisplayModel(
                        name = recipe.name,
                        image = ImageDisplayModel.Remote(recipe.imageUrl),
                        isFavorite = false,
                    )
                }.orEmpty()

        }

        RecipeListGrid(
            recipes = recipes,
            contentPadding = PaddingValues(16.dp),
        )
    }
}
