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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mcloo.recipes.shared.ui.components.ImageWrapper
import com.mcloo.recipes.shared.ui.displaymodels.IngredientDisplayModel
import com.mcloo.recipes.shared.ui.displaymodels.RecipeDetailDisplayModel

private val EXPANDED_TOOLBAR_HEIGHT = 192.dp
private val COLLAPSED_TOOLBAR_HEIGHT = 56.dp

@Composable
private fun Dp.convertToPx(): Float {
    return with(LocalDensity.current) {
        this@convertToPx.roundToPx().toFloat()
    }
}

data class ScrollingToolbarState(
    val toolbarHeightDp: Dp,
    val scrollRatio: Float,
    val nestedScrollConnection: NestedScrollConnection,
)

@Composable
fun rememberScrollingToolbarState(): ScrollingToolbarState {
    val expandedToolbarHeightPx = EXPANDED_TOOLBAR_HEIGHT.convertToPx()
    val collapsedToolbarHeightPx = COLLAPSED_TOOLBAR_HEIGHT.convertToPx()

    val toolbarOffsetHeightPx = remember {
        mutableStateOf(0f)
    }

    val toolbarHeightDp = with(LocalDensity.current) {
        EXPANDED_TOOLBAR_HEIGHT + toolbarOffsetHeightPx.value.toDp()
    }

    // Compare toolbarHeightDp to expandedToolbarHeight
    // Using this ratio, we can determine how much the user scrolled,
    // and use that to scale up/down images or text.
    val totalScrollDistance = (EXPANDED_TOOLBAR_HEIGHT - COLLAPSED_TOOLBAR_HEIGHT)
    val availableScrollDistance = (toolbarHeightDp - COLLAPSED_TOOLBAR_HEIGHT)
    val ratio = availableScrollDistance / totalScrollDistance
    println("ADAMLOG - RATIO: $ratio")

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                val minOffset = -expandedToolbarHeightPx + collapsedToolbarHeightPx
                toolbarOffsetHeightPx.value = newOffset.coerceIn(minOffset, 0f)

                // Watch the scroll, but don't do anything,
                // so the lazy column still scrolls normally.
                return Offset.Zero
            }
        }
    }

    return remember(
        toolbarOffsetHeightPx,
        ratio,
        nestedScrollConnection,
    ) {
        ScrollingToolbarState(
            toolbarHeightDp = toolbarHeightDp,
            scrollRatio = ratio,
            nestedScrollConnection = nestedScrollConnection,
        )
    }
}

@Composable
fun RecipeDetailContent(
    recipe: RecipeDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    val state = rememberScrollingToolbarState()

    Surface {
        Box(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(state.nestedScrollConnection),
        ) {
            RecipeInformationList(
                recipe = recipe,
                contentPadding = PaddingValues(top = state.toolbarHeightDp),
            )

            RecipeDetailHeader(
                recipe = recipe,
                expandedRatio = state.scrollRatio,
                modifier = Modifier
                    .height(state.toolbarHeightDp),
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
//            .aspectRatio(RECIPE_HEADER_ASPECT_RATIO),
    ) {
        ImageWrapper(
            image = recipe.image,
            contentDescription = "${recipe.name} Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(expandedRatio),
        )

        RecipeName(
            name = recipe.name,
            modifier = Modifier
                .align(Alignment.BottomStart),
        )
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
