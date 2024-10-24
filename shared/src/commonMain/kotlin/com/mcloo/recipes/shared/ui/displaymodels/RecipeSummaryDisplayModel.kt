package com.mcloo.recipes.shared.ui.displaymodels

import com.mcloo.recipes.shared.data.models.Recipe

data class RecipeSummaryDisplayModel(
    val name: String,
    val image: ImageDisplayModel,
    val isFavorite: Boolean,
) {
    constructor(recipe: Recipe) : this(
        name = recipe.name,
        image = ImageDisplayModel.Remote(recipe.imageUrl),
        isFavorite = false,
    )
}
