package com.mcloo.recipes.shared.recipelist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.ui.components.RecipeListGrid
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel

@Composable
fun RecipeListContent(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    recipes: List<RecipeSummaryDisplayModel>,
    onRecipeClicked: (String) -> Unit,
    showLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize(),
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            shape = CircleShape,
            placeholder = {
                Text(
                    text = "Search",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        AnimatedVisibility(
            visible = showLoading,
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

        RecipeListGrid(
            recipes = recipes,
            contentPadding = PaddingValues(16.dp),
            onRecipeClicked = onRecipeClicked,
            modifier = modifier,
        )
    }
}
