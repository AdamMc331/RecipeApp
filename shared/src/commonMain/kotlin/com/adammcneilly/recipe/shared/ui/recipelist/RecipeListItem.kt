package com.adammcneilly.recipe.shared.ui.recipelist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeListItem(
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineText = {
            Text(
                text = "Spam Fried Rice",
            )
        },
        overlineText = {
            Text(
                text = "30 – 40 minutes",
            )
        },
        supportingText = {
            Text(
                text = "Supporting Text",
            )
        },
        modifier = modifier,
    )
}
