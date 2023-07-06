package com.adammcneilly.recipe.shared.ui.displaymodels

/**
 * User friendly representation of an ingredient in a recipe, and the amount
 * needed.
 *
 * @property[name] The description of the ingredient, for example: eggs.
 * @property[amount] User friendly description of the amount required. May or may not include
 * a measurement unit. Examples: 3, 1 tbsp, 5 cups.
 */
data class IngredientAmountDisplayModel(
    val name: String,
    val amount: String,
)
