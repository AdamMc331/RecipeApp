package com.mcloo.recipes.shared.ui.displaymodels

import com.mcloo.recipes.shared.data.models.Recipe

data class RecipeDetailDisplayModel(
    val name: String,
    val image: ImageDisplayModel,
    val isFavorite: Boolean,
    val ingredients: List<IngredientDisplayModel>,
) {
    constructor(recipe: Recipe) : this(
        name = recipe.name,
        image = ImageDisplayModel.Remote(recipe.imageUrl),
        isFavorite = false,
        ingredients = recipe.ingredients.map { ingredient ->
            IngredientDisplayModel(
                name = ingredient.name,
                measurement = ingredient.measurement,
            )
        },
    )
}
