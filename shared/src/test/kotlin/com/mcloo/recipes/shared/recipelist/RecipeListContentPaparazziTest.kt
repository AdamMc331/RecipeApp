package com.mcloo.recipes.shared.recipelist

import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.BasePaparazziTest
import com.mcloo.recipes.shared.Res
import com.mcloo.recipes.shared.oxtail
import com.mcloo.recipes.shared.ui.displaymodels.ImageDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel
import kotlin.test.Test

class RecipeListContentPaparazziTest : BasePaparazziTest() {
    @Test
    fun renderWithoutSearch() {
        snapshot(
            screenPadding = 0.dp,
        ) {
            RecipeListContent(
                searchQuery = "",
                onSearchQueryChanged = {},
                recipes = emptyList(),
                onRecipeClicked = {},
            )
        }
    }

    @Test
    fun renderWithSearch() {
        val query = "Oxtail"

        val recipes = List(10) { index ->
            RecipeSummaryDisplayModel(
                id = "123",
                name = "Oxtail with broad beans",
                image = ImageDisplayModel.Local(
                    Res.drawable.oxtail,
                ),
                isFavorite = false,
            )
        }

        snapshot(
            screenPadding = 0.dp,
        ) {
            RecipeListContent(
                searchQuery = query,
                onSearchQueryChanged = {},
                recipes = recipes,
                onRecipeClicked = {},
            )
        }
    }
}
