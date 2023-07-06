package com.adammcneilly.recipe.shared

import com.adammcneilly.recipe.shared.ui.displaymodels.IngredientAmountDisplayModel
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel

@Suppress("MagicNumber")
object TestDataProvider {

    fun provideIngredient(): IngredientAmountDisplayModel {
        val names = listOf(
            "Eggs",
            "Flour",
            "Rice",
            "Onions",
            "Garlic",
            "Milk",
            "Peppers",
            "Butter",
            "Red Pepper Flakes",
            "Paprika",
        )

        val amounts = listOf(
            "1",
            "2",
            "3",
            "2 tsp",
            "4 tbsp",
            "1 cup",
            "1.5 cups",
        )

        return IngredientAmountDisplayModel(
            name = names.random(),
            amount = amounts.random(),
        )
    }

    fun provideRecipes(
        count: Int = 15,
    ): List<RecipeDisplayModel> {
        val timeFrames = listOf(
            "15 Minutes",
            "30 Minutes",
            "1 Hour",
            "1.5 Hours",
        )

        val mealTags = listOf(
            "Breakfast",
            "Lunch",
            "Dinner",
        )

        val difficultyTags = listOf(
            "Easy",
            "Medium",
            "Hard",
        )

        return (1..count).map { index ->
            RecipeDisplayModel(
                timeFrame = timeFrames.random(),
                name = "Recipe $index",
                tags = listOf(
                    mealTags.random(),
                    difficultyTags.random(),
                ),
                ingredients = (1..5).map {
                    provideIngredient()
                },
            )
        }
    }
}
