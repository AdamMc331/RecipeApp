package com.mcloo.recipes.shared.data.mealdb

import com.mcloo.recipes.shared.data.BaseKtorClient
import com.mcloo.recipes.shared.data.RecipeService
import com.mcloo.recipes.shared.data.models.Ingredient
import com.mcloo.recipes.shared.data.models.Recipe

class MealDBRecipeService(
    private val apiClient: BaseKtorClient = MealDBKtorClient,
) : RecipeService {
    override suspend fun getRecipesByName(name: String): Result<List<Recipe>> {
        val recipeListResult = apiClient.getResponse<MealDBRecipeListDTO>(
            endpoint = "/search.php",
            params = mapOf("s" to name),
        )

        return recipeListResult.mapCatching { listResult ->
            val meals = listResult.meals

            if (meals == null) {
                throw IllegalArgumentException("Unable to parse null recipes for name: $name")
            } else {
                return@mapCatching meals.map(MealDBMealDTO::toRecipe)
            }
        }
    }

    override suspend fun getRecipeById(id: String): Result<Recipe> {
        val recipeResult = apiClient.getResponse<MealDBRecipeListDTO>(
            endpoint = "/lookup.php",
            params = mapOf("i" to id),
        )

        return recipeResult.mapCatching { listResult ->
            val recipeDTO = listResult.meals?.firstOrNull()

            if (recipeDTO == null) {
                throw IllegalArgumentException("Unable to parse null recipe for id: $id")
            } else {
                return@mapCatching recipeDTO.toRecipe()
            }
        }
    }
}

private fun MealDBMealDTO.toRecipe(): Recipe {
    return Recipe(
        id = this.idMeal.orEmpty(),
        name = strMeal.orEmpty(),
        imageUrl = strMealThumb.orEmpty(),
        ingredients = this.ingredients(),
    )
}

private fun MealDBMealDTO.ingredients(): List<Ingredient> {
    val ingredient1 = Ingredient(
        name = strIngredient1.orEmpty(),
        measurement = strMeasure1.orEmpty(),
    )

    val ingredient2 = Ingredient(
        name = strIngredient2.orEmpty(),
        measurement = strMeasure2.orEmpty(),
    )

    val ingredient3 = Ingredient(
        name = strIngredient3.orEmpty(),
        measurement = strMeasure3.orEmpty(),
    )

    val ingredient4 = Ingredient(
        name = strIngredient4.orEmpty(),
        measurement = strMeasure4.orEmpty(),
    )

    val ingredient5 = Ingredient(
        name = strIngredient5.orEmpty(),
        measurement = strMeasure5.orEmpty(),
    )

    return listOf(
        ingredient1,
        ingredient2,
        ingredient3,
        ingredient4,
        ingredient5,
    )
}
