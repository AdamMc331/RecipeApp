package com.mcloo.recipes.shared.ui.displaymodels

import com.mcloo.recipes.shared.data.models.Recipe

data class RecipeSummaryDisplayModel(
    val id: String,
    val name: String,
    val image: ImageDisplayModel,
    val isFavorite: Boolean,
    val isPlaceholder: Boolean = false,
) {
    constructor(recipe: Recipe) : this(
        id = recipe.id,
        name = recipe.name,
        image = ImageDisplayModel.Remote(recipe.imageUrl),
        isFavorite = false,
    )

    companion object {
        val PLACEHOLDER = RecipeSummaryDisplayModel(
            id = "",
            name = "",
            image = ImageDisplayModel.Placeholder,
            isFavorite = false,
            isPlaceholder = true,
        )
    }
}
