package com.adammcneilly.recipe.shared.ui.displaymodels

data class RecipeDisplayModel(
    val timeFrame: String,
    val name: String,
    val tags: List<String>,
    val ingredients: List<IngredientAmountDisplayModel>,
    val steps: List<RecipeStepDisplayModel>,
)
