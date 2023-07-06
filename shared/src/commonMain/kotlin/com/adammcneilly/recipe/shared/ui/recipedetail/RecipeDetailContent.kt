package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

/**
 * This is the actual UI to render inside a [RecipeDetailScreen].
 */
@OptIn(ExperimentalMaterial3Api::class)
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
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.titleLarge,
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            recipe.tags.forEach { tag ->
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
                )
            }
        }

        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleMedium,
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                recipe.ingredients.forEachIndexed { index, ingredient ->
                    Row(
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = ingredient.name,
                            modifier = Modifier
                                .weight(1F),
                        )

                        Text(
                            text = ingredient.amount,
                        )
                    }

                    if (index != recipe.ingredients.lastIndex) {
                        Divider(
                            color = MaterialTheme.colorScheme.outline,
                        )
                    }
                }
            }
        }
    }
}
