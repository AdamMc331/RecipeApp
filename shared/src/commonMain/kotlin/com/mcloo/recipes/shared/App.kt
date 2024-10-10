package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.mcloo.recipes.shared.ui.components.RecipeListGrid
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        val recipes = List(10) { index ->
            RecipeSummaryDisplayModel(
                name = "Spicy Arrabiata Penne: $index",
                image = ImageDisplayModel.Remote(
                    url = "https://www.themealdb.com/images/media/meals/1520083578.jpg",
                ),
                isFavorite = false,
            )
        }

        RecipeListGrid(recipes)
    }
}
