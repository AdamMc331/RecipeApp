package com.adammcneilly.recipe.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
