package com.adammcneilly.recipe.shared

import com.adammcneilly.recipe.shared.ui.displaymodels.IngredientAmountDisplayModel
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeDisplayModel
import com.adammcneilly.recipe.shared.ui.displaymodels.RecipeStepDisplayModel

@Suppress("MagicNumber")
object TestDataProvider {

    fun provideVeggieOmelette(): RecipeDisplayModel {
        return RecipeDisplayModel(
            timeFrame = "15 Minutes",
            name = "Veggie Omelette",
            tags = listOf("Breakfast", "Easy", "Healthy"),
            ingredients = listOf(
                IngredientAmountDisplayModel(
                    name = "Salt",
                    amount = "",
                ),
                IngredientAmountDisplayModel(
                    name = "Pepper",
                    amount = "",
                ),
                IngredientAmountDisplayModel(
                    name = "Olive Oil",
                    amount = "",
                ),
                IngredientAmountDisplayModel(
                    name = "Eggs",
                    amount = "3",
                ),
                IngredientAmountDisplayModel(
                    name = "Bell Pepper",
                    amount = "0.5",
                ),
                IngredientAmountDisplayModel(
                    name = "Onion",
                    amount = "0.25",
                ),
                IngredientAmountDisplayModel(
                    name = "Mushrooms",
                    amount = "2",
                ),
                IngredientAmountDisplayModel(
                    name = "Spinach",
                    amount = "0.5 Cups",
                ),
                IngredientAmountDisplayModel(
                    name = "Shredded Cheese",
                    amount = "0.25 Cups",
                ),
            ),
            steps = listOf(
                RecipeStepDisplayModel(
                    index = 1,
                    instructions = "Heat olive oil over medium high heat.",
                ),
                RecipeStepDisplayModel(
                    index = 2,
                    instructions = "Add veggies and cook until slightly browned, about 5 minutes.",
                ),
                RecipeStepDisplayModel(
                    index = 3,
                    instructions = "Set veggies aside on a plate.",
                ),
                RecipeStepDisplayModel(
                    index = 4,
                    instructions = "Crack and whisk the eggs, and add to the pan. Cook until mostly dry.",
                ),
                RecipeStepDisplayModel(
                    index = 5,
                    instructions = "Add veggies to one half of the eggs along with shredded cheese. Season with salt and pepper and flip omelette shut.",
                ),
                RecipeStepDisplayModel(
                    index = 6,
                    instructions = "Cook until cheese has melted and then serve.",
                ),
            ),
        )
    }

    private fun provideIngredient(): IngredientAmountDisplayModel {
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
            provideVeggieOmelette()
//            RecipeDisplayModel(
//                timeFrame = timeFrames.random(),
//                name = "Recipe $index",
//                tags = listOf(
//                    mealTags.random(),
//                    difficultyTags.random(),
//                ),
//                ingredients = (1..5).map {
//                    provideIngredient()
//                },
//                steps = emptyList(),
//            )
        }
    }
}
