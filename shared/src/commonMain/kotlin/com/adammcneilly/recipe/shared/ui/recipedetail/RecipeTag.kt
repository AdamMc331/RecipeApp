package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTag(
    tag: String,
    modifier: Modifier = Modifier,
) {
    SuggestionChip(
        onClick = {},
        label = {
            Text(
                text = tag,
            )
        },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        modifier = modifier,
    )
}
