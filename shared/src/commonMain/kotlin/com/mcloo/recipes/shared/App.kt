package com.mcloo.recipes.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.theme.RecipeTheme
import com.mcloo.recipes.shared.ui.components.RecipeListItemCard
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun App() {
    RecipeTheme {
        RecipeListItemCard(
            recipe = RecipeSummaryDisplayModel(
                name = "Spicy Arrabiata Penne",
                imageUrl = "https://www.themealdb.com/images/media/meals/1520083578.jpg",
            ),
            modifier = Modifier
                .padding(16.dp),
        )
    }
}
