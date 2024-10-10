package com.mcloo.recipes.shared.ui.displaymodels

import org.jetbrains.compose.resources.DrawableResource

sealed interface ImageDisplayModel {
    data class Remote(
        val url: String,
    ) : ImageDisplayModel

    data class Local(
        val resource: DrawableResource,
    ) : ImageDisplayModel
}
