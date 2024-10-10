package com.mcloo.recipes.shared.ui.components

import com.mcloo.recipes.shared.BasePaparazziTest
import com.mcloo.recipes.shared.Res
import com.mcloo.recipes.shared.oxtail
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import org.junit.Test

class RecipeListItemCardPaparazziTest : BasePaparazziTest() {
    @Test
    fun render() {
        val recipe = RecipeSummaryDisplayModel(
            name = "Oxtail with broad beans",
            image = ImageDisplayModel.Local(
                Res.drawable.oxtail,
            ),
        )

        snapshot {
            RecipeListItemCard(recipe)
        }
    }
}
