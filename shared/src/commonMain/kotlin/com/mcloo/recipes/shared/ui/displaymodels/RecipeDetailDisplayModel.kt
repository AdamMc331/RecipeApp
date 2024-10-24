package com.mcloo.recipes.shared.ui.displaymodels

import com.mcloo.recipes.shared.data.models.Recipe

data class RecipeDetailDisplayModel(
    val id: String,
    val name: String,
    val image: ImageDisplayModel,
    val isFavorite: Boolean,
    val ingredients: List<IngredientDisplayModel>,
) {
    constructor(recipe: Recipe) : this(
        id = recipe.id,
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
