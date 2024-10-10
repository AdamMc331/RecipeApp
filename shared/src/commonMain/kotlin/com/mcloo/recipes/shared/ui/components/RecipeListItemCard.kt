package com.mcloo.recipes.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mcloo.recipes.shared.ui.displaymodels.RecipeSummaryDisplayModel

@Composable
fun RecipeListItemCard(
    recipe: RecipeSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F),
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = "${recipe.name} Image",
                modifier = Modifier
                    .fillMaxSize(),
            )

            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black,
                            ),
                        ),
                    ).fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
            )
        }
    }
}
