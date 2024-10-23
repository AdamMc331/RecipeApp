package com.mcloo.recipes.shared.data.mealdb

import com.mcloo.recipes.shared.data.BaseKtorClient

// https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata
object MealDBKtorClient : BaseKtorClient("https://www.themealdb.com/api/json/v1/1")
