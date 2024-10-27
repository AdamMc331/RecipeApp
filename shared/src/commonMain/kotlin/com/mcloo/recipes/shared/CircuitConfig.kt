package com.mcloo.recipes.shared

import com.mcloo.recipes.shared.recipedetail.RecipeDetailScreen
import com.mcloo.recipes.shared.recipelist.RecipeListScreen
import com.slack.circuit.foundation.Circuit

val circuitConfig = Circuit
    .Builder()
    .addUiFactory(
        RecipeListScreen.UiFactory,
        RecipeDetailScreen.UiFactory,
    ).addPresenterFactory(
        RecipeListScreen.PresenterFactory,
        RecipeDetailScreen.PresenterFactory,
    ).build()
