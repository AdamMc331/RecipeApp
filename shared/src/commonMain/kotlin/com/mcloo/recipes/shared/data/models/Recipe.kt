package com.mcloo.recipes.shared.data.models

data class Recipe(
    val id: String,
    val name: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
)
