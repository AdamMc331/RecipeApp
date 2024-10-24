package com.mcloo.recipes.shared.ui.displaymodels

data class RecipeDetailDisplayModel(
    val name: String,
    val image: ImageDisplayModel,
    val isFavorite: Boolean,
    val ingredients: List<IngredientDisplayModel>,
)
