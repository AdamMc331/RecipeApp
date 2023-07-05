package com.adammcneilly.recipe.shared.ui.recipelist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(5) { index ->
            RecipeListItem()

            if (index < 4) {
                Divider()
            }
        }
    }
}
