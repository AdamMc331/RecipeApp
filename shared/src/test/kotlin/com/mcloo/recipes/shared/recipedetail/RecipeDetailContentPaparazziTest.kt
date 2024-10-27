package com.mcloo.recipes.shared.recipedetail

import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.BasePaparazziTest
import com.mcloo.recipes.shared.Res
import com.mcloo.recipes.shared.oxtail
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.IngredientDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel
import org.junit.Test

class RecipeDetailContentPaparazziTest : BasePaparazziTest() {
    @Test
    fun renderDefault() {
        val recipe = RecipeDetailDisplayModel(
            id = "123",
            name = "Oxtail with broad beans",
            image = ImageDisplayModel.Local(
                Res.drawable.oxtail,
            ),
            isFavorite = false,
            ingredients = listOf(
                IngredientDisplayModel(
                    name = "Oxtail",
                    measurement = "450g",
                ),
                IngredientDisplayModel(
                    name = "Onions",
                    measurement = "1 chopped",
                ),
                IngredientDisplayModel(
                    name = "Spring Onions",
                    measurement = "1",
                ),
            ),
        )

        snapshot(
            screenPadding = 0.dp,
        ) {
            RecipeDetailContent(recipe)
        }
    }
}
