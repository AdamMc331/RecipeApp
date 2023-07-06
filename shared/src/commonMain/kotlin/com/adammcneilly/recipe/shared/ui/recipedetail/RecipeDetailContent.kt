package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

/**
 * This is the actual UI to render inside a [RecipeDetailScreen].
 */
@Composable
fun RecipeDetailContent(
    recipe: RecipeDisplayModel,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Recipe Detail For: ${recipe.name}",
        modifier = modifier
            .padding(32.dp),
    )
}
