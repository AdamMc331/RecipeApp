package com.mcloo.recipes.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel

@Composable
fun RecipeListGrid(
    recipes: List<RecipeSummaryDisplayModel>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Make adaptive
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        items(recipes.size) { index ->
            val recipe = recipes[index]
            RecipeListItemCard(recipe)
        }
    }
}
