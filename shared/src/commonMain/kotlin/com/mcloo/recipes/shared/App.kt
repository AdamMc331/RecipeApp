package com.mcloo.recipes.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
        rememberCoroutineScope().launch {
            MealDBRecipeService().getRecipesByName("Arrabiata")
        }

        RecipeListItemCard(
            recipe = RecipeSummaryDisplayModel(
                name = "Spicy Arrabiata Penne",
                image = ImageDisplayModel.Remote(
                    url = "https://www.themealdb.com/images/media/meals/1520083578.jpg",
                ),
            )
        }

        RecipeListGrid(recipes)
    }
}
