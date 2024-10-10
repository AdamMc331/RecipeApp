package com.mcloo.recipes.shared.ui.components

import com.mcloo.recipes.shared.BasePaparazziTest
import com.mcloo.recipes.shared.Res
import com.mcloo.recipes.shared.oxtail
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import org.junit.Test

class RecipeListGridPaparazziTest : BasePaparazziTest() {
    @Test
    fun render() {
        val recipes = List(10) { index ->
            RecipeSummaryDisplayModel(
                name = "Oxtail with broad beans",
                image = ImageDisplayModel.Local(
                    Res.drawable.oxtail,
                ),
                isFavorite = false,
            )
        }

        snapshot {
            RecipeListGrid(recipes)
        }
    }
}
