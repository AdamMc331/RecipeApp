package com.mcloo.recipes.shared.data.mealdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDBRecipeListDTO(
    @SerialName("meals")
    val meals: List<MealDBMealDTO>? = null,
)
