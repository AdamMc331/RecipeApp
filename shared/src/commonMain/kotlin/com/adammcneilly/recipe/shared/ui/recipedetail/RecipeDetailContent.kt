package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RecipeNameTitle(recipe)

        RecipeTagCarousel(
            tags = recipe.tags,
        )

        IngredientsTitle()

        IngredientListCard(
            ingredients = recipe.ingredients,
        )
    }
}

@Composable
private fun RecipeNameTitle(recipe: RecipeDisplayModel) {
    Text(
        text = recipe.name,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun IngredientsTitle() {
    Text(
        text = "Ingredients",
        style = MaterialTheme.typography.titleMedium,
    )
}
