package com.adammcneilly.recipe.shared.ui.recipelist

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListItem(
    recipe: RecipeDisplayModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineText = {
            Text(
                text = recipe.name,
            )
        },
        overlineText = {
            Text(
                text = recipe.timeFrame,
            )
        },
        modifier = modifier
            .clickable(onClick = onClick),
    )
}
