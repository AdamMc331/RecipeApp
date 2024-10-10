package com.mcloo.recipes.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            ImageWrapper(
                image = recipe.image,
                contentDescription = "${recipe.name} Image",
                modifier = Modifier
                    .fillMaxSize(),
            )

            RecipeName(
                name = recipe.name,
                modifier = Modifier
                    .align(Alignment.BottomStart),
            )

            FavoriteButton(
                onClick = {
                    // Handle favorite click,
                },
                isFavorite = recipe.isFavorite,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd),
            )
        }
    }
}

@Composable
private fun RecipeName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black,
                    ),
                ),
            ).fillMaxWidth()
            .padding(16.dp),
    )
}

@Composable
private fun FavoriteButton(
    onClick: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier,
) {
    val icon = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(8.dp),
            ),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
        )
    }
}
