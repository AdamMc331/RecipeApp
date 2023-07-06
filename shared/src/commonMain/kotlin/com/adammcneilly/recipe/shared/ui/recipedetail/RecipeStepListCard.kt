package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeStepDisplayModel

@Composable
fun RecipeStepListCard(
    steps: List<RecipeStepDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column {
            steps.forEachIndexed { index, recipeStep ->
                RecipeStepListItem(
                    step = recipeStep,
                )

                if (index != steps.lastIndex) {
                    Divider(
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}
