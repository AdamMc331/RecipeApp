package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.recipe.shared.ui.displaymodels.IngredientAmountDisplayModel

@Composable
fun IngredientListCard(
    ingredients: List<IngredientAmountDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Column {
            ingredients.forEachIndexed { index, ingredient ->
                IngredientListItem(ingredient)

                if (index != ingredients.lastIndex) {
                    Divider(
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}
