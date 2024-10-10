package com.mcloo.recipes.shared.data

import com.mcloo.recipes.shared.data.models.Recipe

interface RecipeService {
    suspend fun getRecipesByName(name: String): Result<List<Recipe>>
}
