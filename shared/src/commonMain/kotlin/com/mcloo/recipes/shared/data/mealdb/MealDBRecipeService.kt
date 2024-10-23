package com.mcloo.recipes.shared.data.mealdb

import com.mcloo.recipes.shared.data.BaseKtorClient
import com.mcloo.recipes.shared.data.RecipeService
import com.mcloo.recipes.shared.data.models.Recipe

class MealDBRecipeService(
    private val apiClient: BaseKtorClient = MealDBKtorClient,
) : RecipeService {
    override suspend fun getRecipesByName(name: String): Result<List<Recipe>> {
        val recipeListResult = apiClient.getResponse<MealDBRecipeListDTO>(
            endpoint = "/search.php",
            params = mapOf("s" to name),
        )

        return recipeListResult.map { listResult ->
            listResult.meals?.map(MealDBMealDTO::toRecipe).orEmpty()
        }
    }
}

private fun MealDBMealDTO.toRecipe(): Recipe {
    return Recipe(
        name = strMeal.orEmpty(),
        imageUrl = strMealThumb.orEmpty(),
    )
}
