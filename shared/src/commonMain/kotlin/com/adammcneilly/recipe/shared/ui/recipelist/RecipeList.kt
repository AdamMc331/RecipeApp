package com.adammcneilly.recipe.shared.ui.recipelist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

@Composable
fun RecipeList(
    recipes: List<RecipeDisplayModel>,
    onRecipeClicked: (RecipeDisplayModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(recipes) { index, recipe ->
            RecipeListItem(
                recipe = recipe,
                onClick = {
                    onRecipeClicked.invoke(recipe)
                },
            )

            if (index != recipes.lastIndex) {
                Divider()
            }
        }
    }
}
