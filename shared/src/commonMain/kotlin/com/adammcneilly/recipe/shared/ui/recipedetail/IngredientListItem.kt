package com.adammcneilly.recipe.shared.ui.recipedetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.recipe.shared.ui.displaymodels.IngredientAmountDisplayModel

@Composable
fun IngredientListItem(
    ingredient: IngredientAmountDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(8.dp),
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
}
