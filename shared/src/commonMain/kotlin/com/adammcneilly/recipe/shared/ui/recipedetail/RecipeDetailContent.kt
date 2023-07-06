package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

/**
 * This is the actual UI to render inside a [RecipeDetailScreen].
 *
 * 1. Step text here
 *    second line starts here.
 * 2. Step text here
 */
@Composable
fun RecipeDetailContent(
    recipe: RecipeDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RecipeNameTitle(recipe)

        RecipeTagCarousel(
            tags = recipe.tags,
        )

        SectionTitle(
            text = "Ingredients",
        )

        IngredientListCard(
            ingredients = recipe.ingredients,
        )

        SectionTitle(
            text = "Instructions",
        )

        RecipeStepListCard(
            steps = recipe.steps,
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
private fun SectionTitle(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
    )
}
