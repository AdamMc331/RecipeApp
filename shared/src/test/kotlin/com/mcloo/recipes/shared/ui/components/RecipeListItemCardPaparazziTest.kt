package com.mcloo.recipes.shared.ui.components

import com.mcloo.recipes.shared.BasePaparazziTest
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import org.junit.Test

class RecipeListItemCardPaparazziTest : BasePaparazziTest() {
    @Test
    fun render() {
        val recipe = RecipeSummaryDisplayModel(
            name = "Spicy Arrabiata Penne",
            imageUrl = "https://www.themealdb.com/images/media/meals/1520083578.jpg",
        )

        snapshot {
            RecipeListItemCard(recipe)
        }
    }
}
