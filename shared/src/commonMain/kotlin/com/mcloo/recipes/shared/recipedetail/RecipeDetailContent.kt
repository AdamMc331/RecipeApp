package com.mcloo.recipes.shared.recipedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.ui.components.CollapsibleToolbarState
import com.mcloo.recipes.shared.ui.components.ImageWrapper
import com.mcloo.recipes.shared.ui.components.rememberCollapsibleToolbarState
import com.mcloo.recipes.shared.ui.displaymodels.IngredientDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel
import com.mcloo.recipes.shared.ui.utils.plus

@Composable
fun RecipeDetailContent(
    recipe: RecipeDetailDisplayModel,
    modifier: Modifier = Modifier,
    collapsibleToolbarState: CollapsibleToolbarState = rememberCollapsibleToolbarState(),
) {
    Surface {
        Box(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(collapsibleToolbarState.nestedScrollConnection),
        ) {
            RecipeInformationList(
                recipe = recipe,
                contentPadding = PaddingValues(
                    top = collapsibleToolbarState.toolbarHeightDp,
                ) + PaddingValues(16.dp),
            )

            RecipeDetailHeader(
                recipe = recipe,
                expandedRatio = collapsibleToolbarState.expandedRatio,
                modifier = Modifier
                    .height(collapsibleToolbarState.toolbarHeightDp),
            )
        }
    }
}

@Composable
private fun RecipeInformationList(
    recipe: RecipeDetailDisplayModel,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        if (recipe.ingredients.isNotEmpty()) {
            item {
                Text(
                    text = "Ingredients",
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            item {
                IngredientListCard(
                    ingredients = recipe.ingredients,
                )
            }
        }
    }
}

@Composable
private fun RecipeDetailHeader(
    recipe: RecipeDetailDisplayModel,
    expandedRatio: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        ImageWrapper(
            image = recipe.image,
            contentDescription = "${recipe.name} Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(expandedRatio),
        )

        RecipeNameToolbar(
            name = recipe.name,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .alpha(1F - expandedRatio),
        )

        RecipeNameWithGradient(
            name = recipe.name,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .alpha(expandedRatio),
        )
    }
}

@Composable
private fun RecipeNameWithGradient(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecipeNameToolbar(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}

@Composable
private fun IngredientListCard(
    ingredients: List<IngredientDisplayModel>,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                ),
        ) {
            ingredients.forEachIndexed { index, ingredient ->
                IngredientRow(
                    ingredient = ingredient,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                        ),
                )

                if (index < ingredients.lastIndex) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
            }
        }
    }
}

@Composable
private fun IngredientRow(
    ingredient: IngredientDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Text(
            text = ingredient.name,
        )

        Spacer(
            modifier = Modifier
                .weight(1F),
        )

        Text(
            text = ingredient.measurement,
        )
    }
}
